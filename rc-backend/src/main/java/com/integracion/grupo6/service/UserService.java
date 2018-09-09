package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(UserDTO user);

    User update(UserDTO user);

    User delete(Long id);

    User findById(Long id);

    List<User> findAll();

    User findByUsername(String username);

}
