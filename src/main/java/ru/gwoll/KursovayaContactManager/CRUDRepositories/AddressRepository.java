package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.gwoll.KursovayaContactManager.Entities.Address;

@RepositoryRestResource
public interface AddressRepository extends CrudRepository<Address, Long> {
}
