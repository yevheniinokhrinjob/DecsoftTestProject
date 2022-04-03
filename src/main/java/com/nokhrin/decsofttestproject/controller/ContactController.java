package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin
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

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveContact(@Valid @RequestBody Contact contact){
        System.out.println(contact.getFirstName());
        //   contactService.saveContact(contact);
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
