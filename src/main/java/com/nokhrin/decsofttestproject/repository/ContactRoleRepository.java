package com.nokhrin.decsofttestproject.repository;

import com.nokhrin.decsofttestproject.model.ContactRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRoleRepository extends JpaRepository<ContactRole, Long> {
        ContactRole findByRole(String role);
}
