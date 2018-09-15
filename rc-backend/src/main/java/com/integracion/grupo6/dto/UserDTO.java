package com.integracion.grupo6.dto;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.domain.UserRole;

public class UserDTO {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String userRole;

    public UserDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
