package com.project.Backend;

import com.project.Backend.DTO.ProductRequestDTO;
import com.project.Backend.DTO.ProductResponseDTO;
import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.controller.ProductController;
import com.project.Backend.entity.Product;
import com.project.Backend.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private ProductRequestDTO productRequestDTO;
    private ProductResponseDTO productResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data setup
        product = new Product();
        product.setIdProduct(1L);
        product.setProductName("Test Product");
        product.setDescription("Test Description");

        productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setProductName("Test Product");
        productRequestDTO.setDescription("Test Description");

        productResponseDTO = new ProductResponseDTO(1L, "Test Product", "Test Description");
    }

    @Test
    void getAllProducts_ReturnsListOfProducts() {
        when(productService.getAllProducts()).thenReturn(List.of(productResponseDTO));
        ResponseEntity<List<ProductResponseDTO>> response = productController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getById_ProductExists_ReturnsProduct() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(Optional.of(productResponseDTO));

        // Act
        ResponseEntity<ProductResponseDTO> response = productController.getById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", Objects.requireNonNull(response.getBody()).getProductName());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void getById_ProductNotFound_ThrowsResourceNotFoundException() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productController.getById(1L));
        assertEquals("Producto no encontrado con el id 1", exception.getMessage());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void createProduct_ValidInput_ReturnsCreatedProduct() {
        // Arrange
        when(productService.createProduct(any(ProductRequestDTO.class))).thenReturn(productResponseDTO);
        //Act & Assert
        ResponseEntity<ProductResponseDTO> response = productController.createProduct(productRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Product", response.getBody().getProductName());
        verify(productService, times(1)).createProduct(any(ProductRequestDTO.class));
    }

    @Test
    void updateProduct_ValidInput_ReturnsUpdatedProduct() {
        // Arrange
        when(productService.updateProduct(eq(1L), any(ProductRequestDTO.class))).thenReturn(productResponseDTO);

        // Act
        ResponseEntity<ProductResponseDTO> response = productController.updateProduct(1L, productRequestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Product", Objects.requireNonNull(response.getBody()).getProductName());
        verify(productService, times(1)).updateProduct(eq(1L), any(ProductRequestDTO.class));
    }

    @Test
    void deleteProduct_ValidId_ReturnsNoContent() {
        // Arrange
        doNothing().when(productService).deleteProduct(1L);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void getProductsByProductName_ProductsFound_ReturnsListOfProducts() {
        // Arrange
        when(productService.getProductsByProductName("Test Product")).thenReturn(List.of(product));
        ResponseEntity<List<ProductResponseDTO>> response = productController.getProductsByProductName("Test Product");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Product", response.getBody().get(0).getProductName());
        verify(productService, times(1)).getProductsByProductName("Test Product");
    }

    @Test
    void getProductsByProductName_NoProductsFound_ThrowsResourceNotFoundException() {
        // Arrange
        when(productService.getProductsByProductName("Unknown Product")).thenReturn(Collections.emptyList());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productController.getProductsByProductName("Unknown Product"));
        assertEquals("No se encontraron productos con el nombre: Unknown Product", exception.getMessage());
        verify(productService, times(1)).getProductsByProductName("Unknown Product");
    }


}