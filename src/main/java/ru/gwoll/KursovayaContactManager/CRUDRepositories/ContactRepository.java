package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.gwoll.KursovayaContactManager.Entities.Contact;
import ru.gwoll.KursovayaContactManager.Entities.Note;

import java.util.List;

@RepositoryRestResource
public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByName(String name);

    @Query("SELECT c FROM Contact c WHERE c.country.name = :country")
    List<Contact> findByCountry(@Param("country") String country);

    @Query("SELECT c FROM Contact c WHERE c.note = :note")
    List<Contact> findByNote(@Param("note") Note note);
}
