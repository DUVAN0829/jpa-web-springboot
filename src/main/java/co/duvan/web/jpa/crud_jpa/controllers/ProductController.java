package co.duvan.web.jpa.crud_jpa.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.duvan.web.jpa.crud_jpa.entities.Product;
import co.duvan.web.jpa.crud_jpa.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Iterable<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Optional<Product> productOptional = service.findById(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.ok().body(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody Product product,
            BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Optional<Product> produOptional = service.update(product, id);

        if (produOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(produOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Product> productOptional = service.delete(service.findById(id).get());

        if (productOptional.isPresent()) {
            return ResponseEntity.ok().body(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    private ResponseEntity<?> validation(BindingResult result) {  

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " el campo " + err.getField() + " " + err.getDefaultMessage());
        });;

        return ResponseEntity.badRequest().body(errors);
    }

}
