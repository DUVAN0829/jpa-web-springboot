package co.duvan.web.jpa.crud_jpa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.duvan.web.jpa.crud_jpa.entities.Role;
import co.duvan.web.jpa.crud_jpa.entities.User;
import co.duvan.web.jpa.crud_jpa.repositories.RoleRepository;
import co.duvan.web.jpa.crud_jpa.repositories.UserRepository;

@Service
public class UserServicesImpl implements UserServices {

    // *Vars */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // *Methods */
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {

            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);

        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // *Codifica el password */

        return userRepository.save(user);
    }

}
