package com.project.Backend.DTO;


import com.project.Backend.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 255, message = "El nombre de usuario no debe exceder los 255 caracteres")
    private String Username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password; // Contraseña en texto plano para el request

    @NotNull(message = "El rol es obligatorio")
    private User.Rol rol;
}
