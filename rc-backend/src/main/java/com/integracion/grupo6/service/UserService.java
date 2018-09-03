package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.User;

public interface UserService {

    User findByUsername(String username);

}
