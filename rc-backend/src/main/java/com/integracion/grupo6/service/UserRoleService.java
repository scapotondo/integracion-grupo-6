package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.UserRole;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserRoleService {

    UserRole findById(Long id) throws EntityNotFoundException;

    List<UserRole> findAll();

    UserRole findByName(String name) throws EntityNotFoundException;

}
