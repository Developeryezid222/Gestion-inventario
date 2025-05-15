package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderdetail")
    private Long idorderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;


    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    @Column(nullable = false)
    private Integer amount;

    @NotNull(message = "El precio unitario al momento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    @Column(name = "unit_price_moment", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitpricemoment;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El subtotal debe ser mayor que cero")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;





}
