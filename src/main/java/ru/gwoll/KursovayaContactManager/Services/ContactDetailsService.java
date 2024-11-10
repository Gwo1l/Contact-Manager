package ru.gwoll.KursovayaContactManager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.ContactRepository;
import ru.gwoll.KursovayaContactManager.Entities.Contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ContactDetailsService implements UserDetailsService {
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ContactDetailsService(ContactRepository contactRepository, PasswordEncoder passwordEncoder) {
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public boolean addContact(Contact contact) {
        try {
            Contact contactFromDb = contactRepository.findByName(contact.getName()).getFirst();
            return false;
        }
        catch (NoSuchElementException e) {
            contact.setPassword(passwordEncoder.encode(contact.getPassword()));
            contactRepository.save(contact);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Contact appContact = contactRepository.findByName(username).getFirst();
            User user = new User(appContact.getName(), appContact.getPassword(), mapRoles(appContact));
            return user;
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private Collection<GrantedAuthority> mapRoles(Contact appContact)
    {
        List<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority("ROLE_" + appContact.getRole().name()));
        return collect;
    }
}
