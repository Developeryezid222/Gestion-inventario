package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.tool.schema.internal.exec.ImprovedExtractionContextImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @NotBlank(message = "El Sku es obligatorio")
    @Size(max = 150, message = "La referencia no debe exceder los 150 caracteres")
    @Column(unique = true, nullable = false)
    private String sku;

    @Size(max = 500 ,message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser minimo")
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = false)
    private Supplier supplier;

    @Column(name = "high_date", nullable = false, updatable = false)
    private LocalDateTime highDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Min(value = 0, message = "El Stock no debe ser minimo")
    @Column(name = "minimun_stock")
    private Integer minimunStock;
}
