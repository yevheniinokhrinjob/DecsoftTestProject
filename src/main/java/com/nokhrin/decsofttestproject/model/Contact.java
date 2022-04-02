package com.nokhrin.decsofttestproject.model;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private boolean enabled;

    @Pattern(regexp="[\\d]{11}")
    private String homePhoneNumber;
    @Pattern(regexp="[\\d]{11}")
    private String workPhoneNumber;

    private String password;

    @Email
   // @Column(unique = true)
    private String email;

    @ManyToMany( fetch = FetchType.EAGER)
    private Set<ContactRole> contactRoles = new HashSet<ContactRole>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ContactRole> getContactRoles() {
        return contactRoles;
    }

    public void setContactRoles(Set<ContactRole> contactRoles) {
        this.contactRoles = contactRoles;
    }
}
