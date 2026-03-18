package com.backend.ecommerce.respository;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
        select distinct o from Order o
        join OrderItem oi on oi.order = o
        join oi.product p
        where lower(p.brand) like lower(concat('%', :brand, '%'))
        """)
    List<Order> findOrderByProductBrand(@Param("brand") String brand);

    @Query("""
        select distinct o from Order o
        join OrderItem oi on oi.order = o
        join oi.product p
        where lower(p.name) like lower(concat('%', :name, '%'))
        """)
    List<Order> findOrderByProductName(@Param("name") String name);

    @Query("""
            select distinct o from Order o
            join OrderItem oi on oi.order = o
            join oi.product p
            where lower(p.category) like lower(concat('%', :category, '%'))
            """)
    List<Order> findOrderByProductCategory(@Param("category") String category);
}
    