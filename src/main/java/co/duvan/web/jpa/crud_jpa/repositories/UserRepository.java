package co.duvan.web.jpa.crud_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import co.duvan.web.jpa.crud_jpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
