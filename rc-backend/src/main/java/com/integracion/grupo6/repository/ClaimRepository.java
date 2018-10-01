package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.Claim;
import com.integracion.grupo6.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Claim findByOrder(Order order);
}
