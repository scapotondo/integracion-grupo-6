package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Product;
import com.integracion.grupo6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(String id) throws EntityNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException("No se encontro el Product con Id " + id);
        }
    }

    @Override
    public boolean exists(String id) {
        try {
            findById(id);
            return true;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
