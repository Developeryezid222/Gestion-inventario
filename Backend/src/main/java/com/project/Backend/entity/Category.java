package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @NotBlank(message = "nombre categoria obligatorio")
    @Size(max = 200, message = "El nombre de la categoria no debe exceder los 200 caracteres")
    @Column(name = "name_category", unique = true, nullable = false)
    private String nameCategory;


    @Size(max = 500, message = "La descripción no debe exceder maximo 500 caracteres")
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


}
