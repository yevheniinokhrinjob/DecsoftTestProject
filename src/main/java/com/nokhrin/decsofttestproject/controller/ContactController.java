package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping
    public List<Contact> getAllContacts(){
       /* Contact contact = new Contact();
        contact.setEmail("sdas");
        contact.setFirstName("A");
        contact.setLastName("B");
        contact.setHomePhoneNumber(12312);
        contact.setWorkPhoneNumber(444);
        contactService.saveContact(contact);*/
        return contactService.listContacts();
    }
    @GetMapping("/number/{number}")
    public Contact getContactByNumber(@PathVariable Integer number){
        return contactService.getContactByHomePhoneNumber(number);
    }
    @GetMapping("/firstName/{firstName}/lastName/{lastName}")
    public List<Contact> getContactsByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
        return contactService.getContactByFirstNameAndLastName(firstName,lastName);
    }
    @PostMapping
    public String saveContact(@RequestBody Contact contact){
        contactService.saveContact(contact);
        return "Contact saved";
    }
    @PatchMapping
    public String editContact(@RequestBody Contact contact){
        contactService.saveContact(contact);
        return "Contact updated";
    }
    @DeleteMapping("/id/{id}")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return "Contact deleted";
    }
}
