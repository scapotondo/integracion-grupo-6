package com.integracion.grupo6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatusRepository, Long> {

}
