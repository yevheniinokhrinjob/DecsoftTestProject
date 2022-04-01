package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    void saveContact(Contact contact);
    void editContact(Contact contact);
    void deleteContact(long id);
    List<Contact> listContacts();
    Contact getContactById(long id);
    Contact getContactByHomePhoneNumber(int number);
    List<Contact> getContactByFirstNameAndLastName(String firstName,String lastName);
}
