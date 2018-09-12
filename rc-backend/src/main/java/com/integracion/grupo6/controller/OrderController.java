package com.integracion.grupo6.controller;

import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping({"/api/order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = {"/{id}"})
    public Order findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }
}