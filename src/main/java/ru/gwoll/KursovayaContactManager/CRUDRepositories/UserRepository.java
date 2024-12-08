package ru.gwoll.KursovayaContactManager.CRUDRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gwoll.KursovayaContactManager.Entities.User;


import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByGender(String gender);

    @Query("SELECT c FROM User c WHERE c.country = :country")
    List<User> findByCountry(@Param("country") String country);

}
