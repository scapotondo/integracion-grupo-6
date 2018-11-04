package com.integracion.grupo6.dto;


public class LogisticsEndpointDTO {

    private String orderId;

    public LogisticsEndpointDTO() {
    }

    public LogisticsEndpointDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
