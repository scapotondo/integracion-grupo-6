package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.OrderStatus;

public interface OrderStatusService {

    OrderStatus getDefaultOrderStatus();

    OrderStatus findById(Long id);

}
