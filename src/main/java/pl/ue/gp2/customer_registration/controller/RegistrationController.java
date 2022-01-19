package pl.ue.gp2.customer_registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.ue.gp2.customer_registration.model.entities.Customer;
import pl.ue.gp2.customer_registration.model.repository.CustomerRepository;
import pl.ue.gp2.customer_registration.model.view.RegistrationData;
import pl.ue.gp2.customer_registration.util.UIUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


@RequestMapping(value = "/registration/")
@Controller
public class RegistrationController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/registerConfirmation")
    public String registerConfirmation(@ModelAttribute RegistrationData registrationData, Model model) {
        if (!UIUtil.isRegistrationDataCorrect(registrationData)) {
            model.addAttribute("message", "Incorrect input data!");
            return "error";
        }
        if (customerRepository.findCustomerByUserEmail(registrationData.getEmail()) != null) {
            model.addAttribute("message", "This email is in use");
            return "error";
        }
        model.addAttribute("registrationData", registrationData);
        Customer customer = UIUtil.transform(registrationData);
        Customer saveVal = customerRepository.save(customer);
        try {
            sendSimpleMessage(saveVal.getUserEmail(), "Please verify your email", "http://localhost:8080/registration/emailConfirmation/" + saveVal.getConfirmationCode() + "/");
        } catch (Exception e) {
            saveVal.setUserStatus("EmailSendError");
            customerRepository.save(saveVal);
            model.addAttribute("message", "Problem with sending email, please check later");
            return "error";
        }

        model.addAttribute("customerId", saveVal.getId());
        return "registerConfirmation";
    }

    @GetMapping(value = "/emailConfirmation/{confirmationId}/")
    public String emailConfirmation(@PathVariable String confirmationId, Model model) {
        Customer customer = customerRepository.findCustomerByConfirmationCode(confirmationId);
        if (customer == null) {
            model.addAttribute("message", "Wrong registration link");
        } else if (!"confirmationRequired".equals(customer.getUserStatus())) {
            model.addAttribute("message", "You have already confirmed");
        } else {
            customer.setUserStatus("EmailConfirmed");
            customerRepository.save(customer);
            model.addAttribute("message", "Email is confirmed");
        }

        return "emailConfirmation";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gp2");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);

    }
}
