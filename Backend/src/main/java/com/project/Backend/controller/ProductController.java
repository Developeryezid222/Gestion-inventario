package com.project.Backend.controller;

import com.project.Backend.DTO.ProductRequestDTO;
import com.project.Backend.DTO.ProductResponseDTO;
import com.project.Backend.Exception.ResourceNotFoundException;


import com.project.Backend.entity.Product;
import com.project.Backend.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> responseDTOs = productService.getAllProducts();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el id " + id));
    }
    @PostMapping("/save")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {

        ProductResponseDTO createProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDTO productRequestDTO){

        ProductResponseDTO updateProduct = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(updateProduct);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }



    // Buscar productos por nombre (puede haber varios)
    @GetMapping("/nombre/{productName}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByProductName(@PathVariable String productName) {
        List<Product> products = productService.getProductsByProductName(productName);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron productos con el nombre: " + productName);
        }
        List<ProductResponseDTO> responseDTOs = products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getIdProduct(),
                        product.getProductName(), // Correcto, ya que Product usa nameProduct
                        product.getDescription()
                ))
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }
}
