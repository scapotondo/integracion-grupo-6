package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Product;

import javax.persistence.EntityNotFoundException;

public interface ProductService {

    Product findById(String id) throws EntityNotFoundException;

    boolean exists(String id);

    Product save(Product product);

}
