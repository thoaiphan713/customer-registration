package pl.ue.gp2.customer_registration.util;

import pl.ue.gp2.customer_registration.model.entities.Customer;

import pl.ue.gp2.customer_registration.model.view.RegistrationData;

import java.time.Instant;

public class UIUtil {

    public static Customer transform(RegistrationData registrationData) {
        Customer customer = new Customer();
        if (registrationData.getEmail() != null) {
            customer.setUserEmail(registrationData.getEmail().toLowerCase());
        }
        customer.setUserPassword(registrationData.getPassword());
        customer.setFirstName(registrationData.getFirstName());
        customer.setLastName(registrationData.getLastName());
        customer.setAddress(registrationData.getAddress());
        customer.setPhone(registrationData.getPhoneNumber());
        customer.setBirthday(registrationData.getBirthday());
        return customer;
    }

    public static boolean isRegistrationDataCorrect(RegistrationData registrationData) {
        if (registrationData.getEmail() == null || registrationData.getEmail().isEmpty() || registrationData.getEmail().isBlank()) {
            return false;
        }
        if (registrationData.getPassword() == null || registrationData.getPassword().isEmpty() || registrationData.getPassword().isBlank()) {
            return false;
        }
        if (registrationData.getFirstName() == null || registrationData.getFirstName().isEmpty() || registrationData.getFirstName().isBlank()) {
            return false;
        }
        if (registrationData.getLastName() == null || registrationData.getLastName().isEmpty() || registrationData.getLastName().isBlank()) {
            return false;
        }
        if (registrationData.getAddress() == null || registrationData.getAddress().isEmpty() || registrationData.getAddress().isBlank()) {
            return false;
        }
        if (registrationData.getPhoneNumber() == null || registrationData.getPhoneNumber().isEmpty() || registrationData.getPhoneNumber().isBlank()) {
            return false;
        }
        if (registrationData.getBirthday() == null || registrationData.getBirthday().toInstant().isAfter(Instant.now())) {
            return false;
        }
        return true;
    }
}
