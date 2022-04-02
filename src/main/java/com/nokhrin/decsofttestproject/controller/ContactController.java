package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;



    @GetMapping
    public List<Contact> getAllContacts(){

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

    @PostMapping("/register")
    public String saveContact(@Valid @RequestBody Contact contact){
    /*public String saveContact(){
       Contact contact = new Contact();
        contact.setEmail("sdas@e.com");
        contact.setFirstName("A");
        contact.setLastName("B");
        contact.setHomePhoneNumber("123121");
        contact.setWorkPhoneNumber("444");
        contact.setPassword("password");*/
     //   System.out.println(result.getErrorCount());
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
