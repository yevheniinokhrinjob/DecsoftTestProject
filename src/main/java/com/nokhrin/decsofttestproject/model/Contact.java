package com.nokhrin.decsofttestproject.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private int homePhoneNumber;

    private int workPhoneNumber;

    private String email;

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

    public int getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(int homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public int getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(int workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
