package com.integracion.grupo6.dto;

import java.io.Serializable;

public class OrderIntegrationDTO implements Serializable {

    private Long id;

    private String amount;

    private String clientIdentification;

    private String clientFullName;

    private String clientEmail;

    private String productId;

    private String productDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public void setClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "SaleDTO{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", clientIdentification='" + clientIdentification + '\'' +
                ", clientFullName='" + clientFullName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
