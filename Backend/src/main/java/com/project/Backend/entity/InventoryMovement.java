package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movements")
    private Long idMovement;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_store", nullable = false)
    private Store store;

    @NotNull(message = "La cantidad de cambio es obligatoria")
    @Min(value = -1000000, message = "La cantidad de cambio es demasiado baja")  // Valores razonables
    @Max(value = 1000000, message = "La cantidad de cambio es demasiado alta")
    @Column(name = "movement_quantity", nullable = false)
    private Integer movementQuantity;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Size(max = 50, message = "El tipo de movimiento no debe exceder los 50 caracteres")
    @Column(name = "movement_type", nullable = false)
    private Enum movementType; // Usar un ENUM o una validación personalizada

    @Column(name = "movement_date", nullable = false, updatable = false)
    private LocalDateTime movementDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = true)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Size(max = 500, message = "La descripción no debe exceder los 500 caracteres")
    private String description;
}
