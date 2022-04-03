package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public List<Contact> getAllContacts() {

        return contactService.listContacts();
    }

    @GetMapping("/number/{number}")
    public List<Contact> getContactByNumber(@PathVariable String number) {
        return contactService.getContactsByHomePhoneNumber(number);
    }

    @GetMapping("lastName/{lastName}")
    public List<Contact> getContactsByLastName(@PathVariable String lastName) {
        return contactService.getContactByLastName(lastName);
    }

    @GetMapping("email/{email}")
    public ResponseEntity getContactsByEmail(@PathVariable String email, HttpServletRequest request) {
        if (isPrincipalOwner(email, request)) {
            return ResponseEntity.ok(contactService.getContactByEmail(email));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No permissions");
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveContact(@Valid @RequestBody Contact contact) {

        try {
            contactService.saveContact(contact);
            return ResponseEntity.ok("Contact saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PatchMapping
    public ResponseEntity editContact(@RequestBody Contact contact, HttpServletRequest request) {

        contact.setEmail(request.getUserPrincipal().getName());
        try {
            contactService.editContact(contact);
            return ResponseEntity.ok("Contact saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity deleteContact(@PathVariable String email, HttpServletRequest request) {
        if (isPrincipalOwner(email, request)) {
            try {
                contactService.deleteContact(email);
                return ResponseEntity.ok("Contact deleted");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No permissions");
        }
    }

    private boolean isPrincipalOwner(String email, HttpServletRequest request) {
        return request.getUserPrincipal().getName().equals(email);
    }
}
