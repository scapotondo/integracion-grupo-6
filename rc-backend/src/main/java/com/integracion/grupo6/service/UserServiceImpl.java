package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserService userService;

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
