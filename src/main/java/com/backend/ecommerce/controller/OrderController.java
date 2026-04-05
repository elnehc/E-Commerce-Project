package com.backend.ecommerce.controller;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderItem;
import com.backend.ecommerce.entity.OrderStatus;
import com.backend.ecommerce.payload.ApiResponse;
import com.backend.ecommerce.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Order lookup endpoints")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List orders", description = "Fetch all orders or filter by order number, status, date range, or product details.")
    public ResponseEntity<ApiResponse<List<Order>>> getOrders(
        @Parameter(description = "Filter by order number") @RequestParam(required = false) String orderNumber,
        @Parameter(description = "Filter by order status") @RequestParam(required = false) OrderStatus status,
        @RequestParam(required = false) 
        @Parameter(description = "Filter from order date (inclusive)") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam(required = false)
        @Parameter(description = "Filter to order date (inclusive)") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @Parameter(description = "Filter by product brand") @RequestParam(required = false) String brand,
        @Parameter(description = "Filter by product name") @RequestParam(required = false) String name,
        @Parameter(description = "Filter by product category") @RequestParam(required = false) String category
    ) {
        List<Order> orders = orderService.getOrders(orderNumber, status, from, to, brand, name, category);
        return ResponseEntity.ok(new ApiResponse<>(
            HttpStatus.OK.value(),
            "Orders fetched successfully.",
            orders
        ));
    }

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get order by id", description = "Fetch a single order by its id.")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(new ApiResponse<>(
            HttpStatus.OK.value(),
            "Order fetched successfully.",
            order
        ));
    }

    @GetMapping(value = "/{orderId}/items", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List order items", description = "Fetch all items belonging to a single order.")
    public ResponseEntity<ApiResponse<List<OrderItem>>> getItemsByOrderId(@PathVariable Integer orderId) {
        List<OrderItem> orderItems = orderService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(new ApiResponse<>(
            HttpStatus.OK.value(),
            "Order items fetched successfully.",
            orderItems
        ));
    }
}
