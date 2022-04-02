package com.nokhrin.decsofttestproject.jwt;

import com.nokhrin.decsofttestproject.model.Contact;
import com.nokhrin.decsofttestproject.model.ContactRole;
import com.nokhrin.decsofttestproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ContactService contactService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Contact contact = contactService.getContactByEmail(username);
        List<GrantedAuthority> authorities= buildUserAuthority(contact.getContactRoles());
        return buildUserForAuthentication(contact, authorities);
    }
    private User buildUserForAuthentication(Contact contact, List<GrantedAuthority> authorities) {

        return new User(contact.getEmail(), contact.getPassword(), contact.isEnabled(),
                true, true, true, authorities);
    }
    private List<GrantedAuthority> buildUserAuthority(Set<ContactRole> contactRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        for (ContactRole contactRole : contactRoles) {
            setAuths.add(new SimpleGrantedAuthority(contactRole.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }
}
