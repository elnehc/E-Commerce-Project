package com.backend.ecommerce.controller;

import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.payload.ApiResponse;
import com.backend.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management endpoints")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List products", description = "Fetch all products or filter by name, brand, or category.")
    public ResponseEntity<ApiResponse<List<Product>>> getProducts(
            @Parameter(description = "Filter by product name") @RequestParam(required = false) String name,
            @Parameter(description = "Filter by product brand") @RequestParam(required = false) String brand,
            @Parameter(description = "Filter by product category") @RequestParam(required = false) String category
    ) {
        List<Product> products = productService.getProducts(name, brand, category);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Products fetched successfully.",
                products
        ));
    }

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get product by id", description = "Fetch a single product by its id.")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product fetched successfully.",
                product
        ));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create product", description = "Create a new product.")
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Product created successfully.",
                createdProduct
        ));
    }

    @PutMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update product", description = "Update an existing product by id.")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Integer productId,
            @RequestBody Product product
    ) {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product updated successfully.",
                updatedProduct
        ));
    }

    @DeleteMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete product", description = "Delete a product by id.")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                "Product deleted successfully.",
                null
        ));
    }
}


/*
    Example: POST /api/products

    Client sends JSON to controller
    Controller receives @RequestBody Product
    Controller calls productService.createProduct(...)
    Service validates and normalizes data
    Service saves through repository
    Repository uses JPA to insert into DB
    Saved entity comes back
    Controller wraps it in ApiResponse
    Client receives 201 Created
*/
