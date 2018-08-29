package com.integracion.grupo6.repository;

import com.integracion.grupo6.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
