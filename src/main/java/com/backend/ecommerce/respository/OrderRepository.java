package com.backend.ecommerce.respository;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderNumber(String orderNumber);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByOrderDateBetween(LocalDate from, LocalDate to);
    List<Order> findByOrderStatusAndOrderDateBetween(
        OrderStatus orderStatus, 
        LocalDate from, 
        LocalDate to
    );
}
    