package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_store")
    private Long idStore;

    @NotBlank(message = "El nombre del almacen es obligatorio")
    @Size(max = 100, message = "El nombre del almacen no debe exceder los 200 caracteres")
    @Column(name = "name_store", unique = true, nullable = false)
    private String nameStore;

    @NotBlank(message = "")
    @Size(max = 200, message = "La ubicación no debe exceder los 200 caracteres")
    private String location;

    @Column(name = "maximun_capacite")
    private Integer maximunCapacite;


    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;


    @OneToMany(mappedBy = "store")
    private List<Inventory> inventories;

}
