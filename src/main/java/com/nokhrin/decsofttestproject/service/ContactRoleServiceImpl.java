package com.nokhrin.decsofttestproject.service;

import com.nokhrin.decsofttestproject.model.ContactRole;
import com.nokhrin.decsofttestproject.repository.ContactRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactRoleServiceImpl  implements ContactRoleService{

    @Autowired
    private ContactRoleRepository contactRoleRepository;
    @Override
    public void addContactRole(ContactRole role) {
        contactRoleRepository.save(role);
    }

    @Override
    public List<ContactRole> listContactRole() {
        return contactRoleRepository.findAll();
    }

    @Override
    public ContactRole getContactRole(String role) {
        return contactRoleRepository.findByRole(role);
    }
}
