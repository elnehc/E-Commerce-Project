package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // these methods are automatically implemented by Spring Data JPA 
    // based on the method name convention
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByBrandContainingIgnoreCase(String brand);
    List<Product> findByCategoryContainingIgnoreCase(String category);
}
