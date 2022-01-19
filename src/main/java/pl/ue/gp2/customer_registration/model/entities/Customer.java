package pl.ue.gp2.customer_registration.model.entities;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="GP2_CUSTOMER")
@DiscriminatorValue("Customer")
public class Customer extends User{

    @Column private String firstName;
    @Column private String lastName;
    @Column private String address;
    @Column private String phone;
    @Column private Date birthday;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
