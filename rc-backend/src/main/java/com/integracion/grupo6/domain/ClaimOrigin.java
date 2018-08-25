package com.integracion.grupo6.domain;

public enum ClaimOrigin {
    WEB("Web"),
    PHONE("Telefono"),
    EMAIL("E-Mail");

    private String name;

    ClaimOrigin(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
