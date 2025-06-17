package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventories")
@IdClass(InventoryId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_store", referencedColumnName = "id_store")
    private Store store;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer amount;

    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime lastUpdateDate = LocalDateTime.now();

}
