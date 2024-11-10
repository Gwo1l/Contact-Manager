package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.gwoll.KursovayaContactManager.Entities.Country;

@RepositoryRestResource
public interface CountryRepository extends CrudRepository<Country, Long>{
}
