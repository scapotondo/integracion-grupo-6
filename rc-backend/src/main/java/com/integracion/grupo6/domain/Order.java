package com.integracion.grupo6.domain;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_")
public class Order {

    @Id
    private Long id;

    @ManyToOne
    private Client client;

    private OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
