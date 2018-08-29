package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}
