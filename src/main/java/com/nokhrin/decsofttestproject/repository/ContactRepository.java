package com.nokhrin.decsofttestproject.repository;


import com.nokhrin.decsofttestproject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findById(long id);
    List<Contact> findByHomePhoneNumber(String number);
    Contact findByEmail(String email);
    List<Contact> findByLastName(String lastName);
}
