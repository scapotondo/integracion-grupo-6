package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

}
