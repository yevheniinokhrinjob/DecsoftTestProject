package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactService {

    void saveContact(Contact contact) throws Exception;
    void editContact(Contact contact) throws Exception;
    void deleteContact(String email) throws Exception;
    List<Contact> listContacts();
    Contact getContactById(long id);
    Contact getContactByEmail(String email);
    List<Contact> getContactsByHomePhoneNumber(String number);
    List<Contact> getContactByLastName(String lastName);
    void addRoleToContact(Long contactId, String role);
}
