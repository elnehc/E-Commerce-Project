package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(String name, String brand, String category) {
        // normalize input to handle nulls and trim whitespace
        name = normalize(name); 
        brand = normalize(brand);
        category = normalize(category);

        if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name);
        }

        if (brand != null) {
            return productRepository.findByBrandContainingIgnoreCase(brand);
        }

        if (category != null) {
            return productRepository.findByCategoryContainingIgnoreCase(category);
        }

        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        return findProductOrThrow(productId);
    }

    public Product createProduct(Product product) {
        Product newProduct = new Product();
        applyProductDetails(newProduct, product);
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer productId, Product product) {
        Product existingProduct = findProductOrThrow(productId);
        applyProductDetails(existingProduct, product);
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer productId) {
        Product existingProduct = findProductOrThrow(productId);
        productRepository.delete(existingProduct);
    }

    private Product findProductOrThrow(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }

    // applies the details from the source product to the target product
    private void applyProductDetails(Product target, Product source) {
        if (source == null) {
            throw new IllegalArgumentException("Product request body is required.");
        }

        target.setName(requireText(source.getName(), "Product name is required."));
        target.setBrand(requireText(source.getBrand(), "Product brand is required."));
        target.setCategory(requireText(source.getCategory(), "Product category is required."));
        target.setDescription(normalize(source.getDescription()));
    }

    private String requireText(String value, String message) {
        String normalizedValue = normalize(value);
        if (normalizedValue == null) {
            throw new IllegalArgumentException(message);
        }
        return normalizedValue;
    }

    
    /**
     * Normalizes a string by trimming whitespace and converting empty strings to null.
     * 
     * @param value the string to normalize
     * @return the normalized string, or null if the input is null or empty after trimming
     */
    private String normalize(String value) {
    
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        return trimmedValue.isEmpty() ? null : trimmedValue;
    }
}
