package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
