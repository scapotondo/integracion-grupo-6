package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void create(UserDTO user);

    void update(UserDTO user);

    void delete(Long id);

    User findById(Long id);

    List<UserDTO> findAll();

    User findByUsername(String username);

    UserDTO findDTOByUsername(String username);

}
