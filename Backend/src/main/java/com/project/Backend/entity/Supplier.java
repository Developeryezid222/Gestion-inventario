package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    private long idSupplier;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre del proveedor no debe exceder los 100 caracteres")
    @Column(name = "name_supplier", nullable = false)
    private String nameSupplier;

    @NotBlank(message = "El nombre del contacto es obligatorio")
    @Size(max = 20, message = "El contacto no puede exceder los 20 caracteres")
    @Column(name = "contact_Telephone", nullable = false)
    private String contactTelephone;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "El email de contacto debe ser valido")
    @Size(max = 100, message = "El email de contacto no debe exceder los 100 caracteres")
    @Column(name = "contact_email")
    private String contactEmail;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección no debe exceder los 255 caracteres")
    @Column(nullable = false)
    private String addres;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
