package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("from Claim c where c.order.id = :orderid")
    Claim findByOrderId(@Param("orderid") Long orderid);
}
