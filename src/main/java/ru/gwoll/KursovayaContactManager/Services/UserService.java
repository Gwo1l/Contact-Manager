package ru.gwoll.KursovayaContactManager.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
}
