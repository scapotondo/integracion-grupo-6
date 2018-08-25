package com.integracion.grupo6.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLAIM")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Order order;

    private ClaimOrigin claimOrigin;

    @ManyToOne
    private ClaimType claimType;

    @ManyToOne
    private ClaimStatus claimStatus;

    private String description;

    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ClaimOrigin getClaimOrigin() {
        return claimOrigin;
    }

    public void setClaimOrigin(ClaimOrigin claimOrigin) {
        this.claimOrigin = claimOrigin;
    }

    public ClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
