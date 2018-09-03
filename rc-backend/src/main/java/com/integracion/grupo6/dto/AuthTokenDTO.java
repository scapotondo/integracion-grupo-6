package com.integracion.grupo6.dto;

public class AuthTokenDTO {

    private String token;

    private String username;

    private String userRole;


    public AuthTokenDTO(String token, String username, String userRole) {
        this.token = token;
        this.username = username;
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
