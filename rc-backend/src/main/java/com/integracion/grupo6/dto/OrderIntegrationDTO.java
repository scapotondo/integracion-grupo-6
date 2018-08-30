package com.integracion.grupo6.dto;

public class OrderIntegrationDTO {

    private Long id;

    private String etaDate;

    private String amount;

    private String clientIdentification;

    private String clientFullName;

    private String clientEmail;

    private Long productId;

    private String productDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtaDate() {
        return etaDate;
    }

    public void setEtaDate(String etaDate) {
        this.etaDate = etaDate;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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
                ", etaDate='" + etaDate + '\'' +
                ", amount='" + amount + '\'' +
                ", clientIdentification='" + clientIdentification + '\'' +
                ", clientFullName='" + clientFullName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
