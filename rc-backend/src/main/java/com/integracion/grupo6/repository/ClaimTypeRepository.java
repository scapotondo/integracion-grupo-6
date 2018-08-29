package com.integracion.grupo6.repository;
import com.integracion.grupo6.domain.ClaimType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypeRepository extends JpaRepository<ClaimType, Long> {

}
