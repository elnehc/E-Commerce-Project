package com.backend.ecommerce.controller;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderItem;
import com.backend.ecommerce.entity.OrderStatus;
import com.backend.ecommerce.service.OrderService;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(
        @RequestParam(required = false) String orderNumber,
        @RequestParam(required = false) OrderStatus status,
        @RequestParam(required = false) 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String category
    ) {
        return orderService.getOrders(orderNumber, status, from, to, brand, name, category);
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping(value = "/{orderId}/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderItem> getItemsByOrderId(@PathVariable Integer orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }


}
