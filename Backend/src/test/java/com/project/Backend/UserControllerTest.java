package com.project.Backend;

import com.project.Backend.DTO.UserRequestDTO;
import com.project.Backend.DTO.UserResponseDTO;
import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.controller.UserController;
import com.project.Backend.entity.User;
import com.project.Backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private List<UserResponseDTO> userResponseDTOList;

    @BeforeEach
    void setUp() {
        // Configuración inicial para los DTOs
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("Claudia");
        userRequestDTO.setPassword("12345");

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setIdUser(1L);
        userResponseDTO.setUserName("John Doe");
        userResponseDTO.setRol(User.Rol.ADMIN);

        userResponseDTOList = Arrays.asList(userResponseDTO);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        when(userService.getAllUsers()).thenReturn(userResponseDTOList);

        // Act
        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTOList, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(userService.getUserById(1L)).thenReturn(Optional.of(userResponseDTO));

        // Act
        ResponseEntity<UserResponseDTO> response = userController.getUserById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void getUserById_ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
        // Arrange
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> userController.getUserById(1L));
        assertEquals("Usuario no encontrado con id 1", exception.getMessage());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void createUser_ShouldReturnCreatedUser() {
        // Arrange
        when(userService.createUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // Act
        ResponseEntity<UserResponseDTO> response = userController.createUser(userRequestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).createUser(any(UserRequestDTO.class));
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        // Arrange
        when(userService.updateUser(anyLong(), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // Act
        ResponseEntity<UserResponseDTO> response = userController.updateUser(1L, userRequestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
        verify(userService, times(1)).updateUser(anyLong(), any(UserRequestDTO.class));
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(userService).deleteUser(anyLong());

        // Act
        ResponseEntity<Void> response = userController.deleteUser(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).deleteUser(1L);
    }
}

