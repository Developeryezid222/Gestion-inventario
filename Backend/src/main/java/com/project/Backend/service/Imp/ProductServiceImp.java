package com.project.Backend.service.Imp;

import com.project.Backend.DTO.ProductRequestDTO;
import com.project.Backend.DTO.ProductResponseDTO;
import com.project.Backend.entity.Product;
import com.project.Backend.repository.ProductRepository;
import com.project.Backend.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public Optional<ProductResponseDTO> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        return null;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO document why this method is empty
    }

    @Override
    public Optional<Product> getProductsBySku(String sku) {
       return productRepository.findBySku(sku);





    }



    @Override
    public List<Product> getProductsByProductName(String productName) {
        return productRepository.findByNameProductContainingIgnoreCase(productName);

    }
}
