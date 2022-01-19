package pl.ue.gp2.customer_registration.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "GP2_USER")
@DiscriminatorColumn(name = "USER_TYPE")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column
    private String userEmail;
    @Column
    private String userPassword;

//    private String userType;
    @Column
    private Date creationDate;
    @Column
    private String userStatus;
    @Column
    private String confirmationCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

//    public String getUserType() {
//        return userType;
//    }
//
//    public void setUserType(String userType) {
//        this.userType = userType;
//    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @PrePersist
    public void prePersist() {
        userStatus = "confirmationRequired";
        creationDate = new Date();
        confirmationCode = UUID.randomUUID().toString();
    }

    @Transient
    public String getUserType(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
        return val == null ? null : val.value();
    }
}
