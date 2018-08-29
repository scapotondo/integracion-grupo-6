package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.UserRole;
import com.integracion.grupo6.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole findById(Long id) throws EntityNotFoundException {
        Optional<UserRole> optionalUserRole = userRoleRepository.findById(id);
        if(optionalUserRole.isPresent()) {
            return optionalUserRole.get();
        } else {
            throw new EntityNotFoundException("No se encontro el UserRole con Id " + id);
        }
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
