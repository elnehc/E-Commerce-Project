package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("""
        select oi from OrderItem oi
        join fetch oi.product
        where oi.order.orderId = :orderId
        """)
    List<OrderItem> findByOrderOrderId(@Param("orderId") Integer orderId);
}
