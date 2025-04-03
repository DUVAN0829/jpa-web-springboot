package co.duvan.web.jpa.crud_jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.duvan.web.jpa.crud_jpa.entities.User;
import co.duvan.web.jpa.crud_jpa.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

    // *Vars */
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

}
