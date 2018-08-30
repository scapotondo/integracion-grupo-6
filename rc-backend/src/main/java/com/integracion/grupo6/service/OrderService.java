package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.Order;

import javax.persistence.EntityNotFoundException;

public interface OrderService {

    boolean exists(Long id);

    Order findById(Long id) throws EntityNotFoundException;

    Order save(Order order);

}
