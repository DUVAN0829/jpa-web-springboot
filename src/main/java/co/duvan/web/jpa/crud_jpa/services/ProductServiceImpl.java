package co.duvan.web.jpa.crud_jpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.duvan.web.jpa.crud_jpa.entities.Product;
import co.duvan.web.jpa.crud_jpa.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Iterable<Product> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Product product, Long id) {

        Optional<Product> optionalProduct = findById(id);

        if (optionalProduct.isPresent()) {

            Product productDb = optionalProduct.get();

            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setDescription(product.getDescription());

            return Optional.of(repository.save(productDb));

        }

        return optionalProduct;

    }

    @Transactional
    @Override
    public Optional<Product> delete(Product product) {

        Optional<Product> productOptional = repository.findById(product.getId());

        productOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });

        return productOptional;
    }

}
