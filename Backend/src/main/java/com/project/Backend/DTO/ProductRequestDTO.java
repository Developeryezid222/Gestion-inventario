package com.project.Backend.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 255, message = "El SKU no debe exceder los 255 caracteres")
    private String sku;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 255, message = "El nombre del producto no debe exceder los 255 caracteres")
    private String productName;

    @Size(max = 500, message = "La descripción del producto no debe exceder los 500 caracteres")
    private String description;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor que cero")
    private BigDecimal unitPrice;


    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinim;
}
