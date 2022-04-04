package com.nokhrin.decsofttestproject.controller;


import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.model.ContactRole;
import com.nokhrin.decsofttestproject.service.ContactRoleService;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Secured("ROLE_ADMIN")
public class ContactRoleController {

    private ContactRoleService contactRoleService;
    private ContactService contactService;
    @Autowired
    public ContactRoleController(ContactRoleService contactRoleService, ContactService contactService){
        this.contactRoleService=contactRoleService;
        this.contactService=contactService;
    }

    @PostMapping("/{role}")
    public String addRole(@PathVariable String role) {
        ContactRole contactRole = new ContactRole();
        contactRole.setRole(role);
        contactRoleService.addContactRole(contactRole);
        return "Role " + role + " added";
    }
    @PostMapping("/{role}/contact/{contactId}")
    public String addRole(@PathVariable String role, @PathVariable Long contactId) {
        try {
            contactService.addRoleToContact(contactId, role);
            return "Role " + role + " assigned to contact " + contactId;
        } catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping()
    public List<ContactRole> getRoles(){
        return contactRoleService.listContactRole();
    }
}
