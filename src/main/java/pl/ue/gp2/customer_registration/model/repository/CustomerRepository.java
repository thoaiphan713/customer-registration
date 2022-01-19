package pl.ue.gp2.customer_registration.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ue.gp2.customer_registration.model.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByConfirmationCode(String confirmationCode); //find customer by the confirmation code that we send

    Customer findCustomerByUserEmail(String userEmail);
}
