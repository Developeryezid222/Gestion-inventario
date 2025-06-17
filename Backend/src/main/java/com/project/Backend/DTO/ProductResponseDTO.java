package com.project.Backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long idProduct;
    private String sku;
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private LocalDateTime allDate;
    private LocalDateTime updatedDate;
    private Integer stockMinim;
    public ProductResponseDTO(Long idProduct,
                              @NotBlank(message = "El nombre del producto es obligatorio")
                              @Size(max = 255, message = "El nombre del producto no debe exceder los 255 caracteres") String productName,
                              @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres") String description) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.description = description;
    }
}
