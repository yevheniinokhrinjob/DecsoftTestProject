package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.exception.ContactNotFoundException;
import com.nokhrin.decsofttestproject.exception.EmailAlreadyExistsException;
import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.model.ContactRole;
import com.nokhrin.decsofttestproject.repository.ContactRepository;
import com.nokhrin.decsofttestproject.repository.ContactRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void saveContact(Contact contact) throws EmailAlreadyExistsException {
        Contact dbContact = contactRepository.findByEmail(contact.getEmail());
        if (dbContact == null) {

            contact.getContactRoles().add(contactRoleRepository.findByRole("ROLE_USER"));
            contact.setPassword(hashPassword(contact.getPassword()));
            contact.setEnabled(true);
            contactRepository.save(contact);
        }else {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    @Override
    @Transactional
    public void editContact(Contact contact) throws ContactNotFoundException {
        Contact dbContact = contactRepository.findByEmail(contact.getEmail());
        if(dbContact!=null) {
            dbContact.setFirstName(contact.getFirstName());
            dbContact.setLastName(contact.getLastName());
            dbContact.setWorkPhoneNumber(contact.getWorkPhoneNumber());
            dbContact.setHomePhoneNumber(contact.getHomePhoneNumber());
            contactRepository.save(dbContact);
        }else {
            throw new ContactNotFoundException("Contact not found");
        }
    }

    @Override
    @Transactional
    public void deleteContact(String email){
        Contact contact = contactRepository.findByEmail(email);
        if(contact!=null) {
            contactRepository.delete(contact);
        }else {
            throw new ContactNotFoundException("Contact not found");
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
    @Transactional
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
