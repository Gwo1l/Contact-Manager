package ru.gwoll.KursovayaContactManager.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users")
    public Collection<User> getAllUsers() {
        return (Collection<User>) userRepository.findAll();
    }

    @Cacheable(value = "users", key="#name")
    public List<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveOrUpdateUser(OidcUser oidcUser) {
        User user = userRepository.findByName(oidcUser.getFullName()).getFirst();
        if (user == null) {
            user = new User();
        }
        user.setName(oidcUser.getFullName());
        userRepository.save(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name).getFirst();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
