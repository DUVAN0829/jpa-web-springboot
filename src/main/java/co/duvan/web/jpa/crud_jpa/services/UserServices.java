package co.duvan.web.jpa.crud_jpa.services;

import java.util.List;

import co.duvan.web.jpa.crud_jpa.entities.User;

public interface UserServices {
    
    List<User> findAll();

    User save(User user);

}
