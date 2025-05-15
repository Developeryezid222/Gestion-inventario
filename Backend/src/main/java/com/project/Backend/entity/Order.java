package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @NotBlank(message = "El tipo de orden es obligatorio")
    @Column(name = "order_type", nullable = false)
    private String orderType;


    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @NotBlank(message =  "El estado del orden es obligatorio")
    @Column(name = "status_order", nullable = false)
    private String statusOrder;

    @ManyToOne
    @JoinColumn(name = "id_supplier", nullable = true)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = true)
    private Customer customer;

    @NotNull(message = "El total de la orden es obligatorio")
    @Column(name = "total_order", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOrder;

    @ManyToOne
    @JoinColumn(name = "id_creation_user")
    private User creationUser;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "order")
    private List<InventoryMovement> inventoryMovements;


}
