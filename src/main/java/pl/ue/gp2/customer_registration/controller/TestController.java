package pl.ue.gp2.customer_registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ue.gp2.customer_registration.model.entities.Customer;
import pl.ue.gp2.customer_registration.model.repository.CustomerRepository;


@RestController
@RequestMapping(value = "/test/")
public class TestController {
    @Autowired //inject spring bin
    private CustomerRepository repository;

    @GetMapping(value = "/xinchao/",produces = "text/plain")
    public String xinchao(){
        Customer customer = new Customer();
        customer.setFirstName("Booboo");
        Customer savedValue = repository.save(customer); //save or update of row in database
        return savedValue.getUserType();
    }

}
