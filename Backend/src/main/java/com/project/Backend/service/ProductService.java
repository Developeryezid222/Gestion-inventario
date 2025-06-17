package com.project.Backend.service;

import com.project.Backend.DTO.ProductRequestDTO;
import com.project.Backend.DTO.ProductResponseDTO;
import com.project.Backend.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    Optional<ProductResponseDTO> getProductById(Long id);
    ProductResponseDTO createProduct(ProductRequestDTO productDTO);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO);
    void deleteProduct(Long id);
    Optional<Product> getProductsBySku(String sku);
     List<Product> getProductsByProductName(String productName);


}
