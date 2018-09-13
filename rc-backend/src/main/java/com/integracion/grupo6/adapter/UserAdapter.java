package com.integracion.grupo6.adapter;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.domain.UserRole;
import com.integracion.grupo6.dto.UserDTO;
import com.integracion.grupo6.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {

    @Autowired
    UserRoleService userRoleService;

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setUserRole(userRoleService.findByName(userDTO.getUserRole()));

        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(user.getFullName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setUserRole(user.getUserRole().getName());

        return userDTO;
    }

}
