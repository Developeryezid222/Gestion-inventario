package com.project.Backend.DTO;


import com.project.Backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long idUser;
    private String UserName;
    private User.Rol rol;
    private LocalDateTime CreationDate;
    private LocalDateTime lastSection;
}
