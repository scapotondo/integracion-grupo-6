package com.integracion.grupo6.domain;

import javax.persistence.*;

@Entity
@Table(name = "CLAIM_TYPE")
public class ClaimType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean logistics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogistics() {
        return logistics;
    }

    public void setLogistics(boolean logistics) {
        this.logistics = logistics;
    }

    @Override
    public String toString() {
        return "ClaimType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logistics=" + logistics +
                '}';
    }
}
