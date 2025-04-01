package co.duvan.web.jpa.crud_jpa.services;

import java.util.Optional;

import co.duvan.web.jpa.crud_jpa.entities.Product;

public interface ProductService {

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Product product, Long id);

    Optional<Product> delete(Product product);

}
