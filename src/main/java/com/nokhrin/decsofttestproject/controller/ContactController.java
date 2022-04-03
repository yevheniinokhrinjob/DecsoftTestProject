package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<Contact> getContactByNumber(@PathVariable String number){
        System.out.println(contactService.getContactsByHomePhoneNumber(number).size());
        return contactService.getContactsByHomePhoneNumber(number);
    }
    @GetMapping("lastName/{lastName}")
    public List<Contact> getContactsByLastName(@PathVariable String lastName){
        return contactService.getContactByLastName(lastName);
    }
    @GetMapping("email/{email}")
    public Contact getContactsByEmail(@PathVariable String email, HttpServletRequest request){
        if(isPrincipalOwner(email,request)) {
            return contactService.getContactByEmail(email);
        }else {
            return null;
        }
    }
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveContact(@Valid @RequestBody Contact contact){

        contactService.saveContact(contact);
            return "Contact saved";
    }
    @PatchMapping
    public String editContact(@RequestBody Contact contact, HttpServletRequest request){

        contact.setEmail(request.getUserPrincipal().getName());
        contactService.editContact(contact);

        return "Contact updated";
    }
    @DeleteMapping("/email/{email}")
    public String deleteContact(@PathVariable String email, HttpServletRequest request){
        System.out.println("Enter");
        if(isPrincipalOwner(email,request)) {
            contactService.deleteContact(email);
            return "Contact deleted";
        }else {
            return "Not deleted";
        }
    }
    private boolean isPrincipalOwner(String email, HttpServletRequest request){
       return request.getUserPrincipal().getName().equals(email);
    }
}
