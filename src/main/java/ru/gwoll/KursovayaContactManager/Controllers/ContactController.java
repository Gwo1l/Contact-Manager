package ru.gwoll.KursovayaContactManager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.ContactRepository;
import ru.gwoll.KursovayaContactManager.Entities.Contact;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/find-by-name")
    public List<Contact> findByName(@RequestParam String name) {
        return contactRepository.findByName(name);
    }

    @GetMapping("/find-by-country")
    public List<Contact> findByCountry(@RequestParam String country) {
        return contactRepository.findByCountry(country);
    }
}
