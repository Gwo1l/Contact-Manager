package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.gwoll.KursovayaContactManager.Entities.PhoneNumber;

@RepositoryRestResource
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
}
