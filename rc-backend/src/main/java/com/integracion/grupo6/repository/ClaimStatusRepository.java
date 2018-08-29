package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Long> {

}
