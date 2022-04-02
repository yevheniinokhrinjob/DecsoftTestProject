package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.ContactRole;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContactRoleService {
    void addContactRole(ContactRole role);
    List<ContactRole> listContactRole();
    ContactRole getContactRole(String role);
}
