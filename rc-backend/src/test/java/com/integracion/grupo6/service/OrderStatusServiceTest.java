package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.OrderStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderStatusServiceTest {

    @Autowired
    private OrderStatusService orderStatusService;

    @Test
    public void getDefaultOrderStatus() {
        OrderStatus defaultOrderStatus = orderStatusService.getDefaultOrderStatus();

        Assert.assertNotNull(defaultOrderStatus);
    }
}