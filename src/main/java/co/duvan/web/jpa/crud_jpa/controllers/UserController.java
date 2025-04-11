package co.duvan.web.jpa.crud_jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.duvan.web.jpa.crud_jpa.entities.User;
import co.duvan.web.jpa.crud_jpa.services.UserServicesImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    // *Vars */
    @Autowired
    private UserServicesImpl services;

    // *Methods */
    @GetMapping
    public List<User> list() {
        return services.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(services.save(user));

    }

    // *Metod validation */
    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " el campo " + err.getField() + " " + err.getDefaultMessage());
        });
        ;

        return ResponseEntity.badRequest().body(errors);
    }

}
