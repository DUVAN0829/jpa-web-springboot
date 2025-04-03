package co.duvan.web.jpa.crud_jpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.duvan.web.jpa.crud_jpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
