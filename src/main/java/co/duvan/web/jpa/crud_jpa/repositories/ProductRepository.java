package co.duvan.web.jpa.crud_jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import co.duvan.web.jpa.crud_jpa.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    

}
