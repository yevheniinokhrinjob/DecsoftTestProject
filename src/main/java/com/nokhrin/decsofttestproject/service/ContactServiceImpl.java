package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.model.ContactRole;
import com.nokhrin.decsofttestproject.repository.ContactRepository;
import com.nokhrin.decsofttestproject.repository.ContactRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {


    private ContactRepository contactRepository;
    private ContactRoleRepository contactRoleRepository;
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactRoleRepository contactRoleRepository){
        this.contactRepository=contactRepository;
        this.contactRoleRepository=contactRoleRepository;
    }

    @Override
    public void saveContact(Contact contact) {
        contact.getContactRoles().add(contactRoleRepository.findByRole("ROLE_USER"));
        contact.setPassword(hashPassword(contact.getPassword()));
        contact.setEnabled(true);
        contactRepository.save(contact);
    }

    @Override
    public void editContact(Contact contact) {
        Contact dbContact = contactRepository.findByEmail(contact.getEmail());
        if(dbContact!=null) {
            dbContact.setFirstName(contact.getFirstName());
            dbContact.setLastName(contact.getLastName());
            dbContact.setWorkPhoneNumber(contact.getWorkPhoneNumber());
            dbContact.setHomePhoneNumber(contact.getHomePhoneNumber());
            contactRepository.save(dbContact);
        }
    }

    @Override
    public void deleteContact(String email) {
        Contact contact = contactRepository.findByEmail(email);
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
    public Contact getContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public List<Contact> getContactsByHomePhoneNumber(String number) {
        return contactRepository.findByHomePhoneNumber(number);
    }

    @Override
    public List<Contact> getContactByLastName(String lastName) {
        return contactRepository.findByLastName( lastName);
    }

    @Override
    public void addRoleToContact(Long contactId, String role) {
        Contact contact = getContactById(contactId);

        ContactRole contactRole = contactRoleRepository.findByRole(role);

        contact.getContactRoles().add(contactRole);
        contactRepository.save(contact);
    }

    private String hashPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
