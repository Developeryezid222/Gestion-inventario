package com.project.Backend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    @NotBlank(message = "El Nombre del usuario es obligatorio")
    @Size(max = 25, message = "El nombre del usuario no puede exceder los 25 caracteres")
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 8, message = "Password debe tener almenos 8 caracteres ")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING) // para mapear el enum  a a String en la DB
    @Column(nullable = false)
    private Rol rol;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "last_session")
    private LocalDateTime lastSession;

    // Definimos un ENUM para los roles
    public enum Rol {
        ADMIN,
        WAREHOUSE_MANAGER,
        SALES
    }

}







