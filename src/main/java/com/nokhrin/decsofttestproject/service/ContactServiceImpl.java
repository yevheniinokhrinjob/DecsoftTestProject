package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void editContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(long id) {
        Contact contact = contactRepository.findById(id);
        if(contact!=null) {
            contactRepository.delete(contact);
        }
    }

    @Override
    public List<Contact> listContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact getContactByHomePhoneNumber(int number) {
        return contactRepository.findByHomePhoneNumber(number);
    }

    @Override
    public List<Contact> getContactByFirstNameAndLastName(String firstName,String lastName) {
        return contactRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
