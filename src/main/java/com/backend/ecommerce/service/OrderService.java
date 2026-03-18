package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderItem;
import com.backend.ecommerce.entity.OrderStatus;
import com.backend.ecommerce.respository.OrderItemRepository;
import com.backend.ecommerce.respository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getOrders(String orderNumber, 
                                OrderStatus status, 
                                LocalDate from, 
                                LocalDate to,
                                String brand,
                                String productName) {
        if (orderNumber != null) {
            return orderRepository.findByOrderNumber(orderNumber);
        }

        if (productName != null) {
            return orderRepository.findOrderByProductName(productName);
        }

        if (brand != null) {
            return orderRepository.findOrderByBrand(brand);
        }

        if (status != null && from != null && to!= null){
            return orderRepository.findByOrderStatusAndOrderDateBetween(status, from, to);
        }

        if (status != null) {
            return orderRepository.findByOrderStatus(status);
        }

        if (from != null && to != null) {
            return orderRepository.findByOrderDateBetween(from, to);
        }

        return orderRepository.findAll();
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        return orderItemRepository.findByOrderOrderId(orderId);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByOrderStatus(status);
    }
    
    public List<Order> getOrdersByDateRange(LocalDate from, LocalDate to) {
        return orderRepository.findByOrderDateBetween(from, to);
    }

}
