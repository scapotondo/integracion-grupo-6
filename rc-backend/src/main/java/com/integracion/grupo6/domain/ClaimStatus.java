package com.integracion.grupo6.domain;

import javax.persistence.*;

@Entity
@Table(name = "CLAIM_STATUS")
public class ClaimStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    @Override
    public String toString() {
        return "ClaimStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
