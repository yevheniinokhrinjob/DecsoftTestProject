package com.nokhrin.decsofttestproject.repository;


import com.nokhrin.decsofttestproject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findById(long id);
}
