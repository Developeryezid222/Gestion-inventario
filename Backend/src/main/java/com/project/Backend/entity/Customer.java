package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no debe exceder los 255 caracteres")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no debe exceder los 100 caracteres")
    private String lastName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email es obligatorio")
    @Size(max = 100, message = "El email no debe exceder los 100 caracteres")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no debe exceder los 20 caracteres")
    private String telephone;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 255, message = "La dirección no debe exceder los 255 caracteres")
    private String addre;


    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
