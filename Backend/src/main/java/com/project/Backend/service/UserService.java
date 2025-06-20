package com.project.Backend.service;


import com.project.Backend.DTO.UserRequestDTO;
import com.project.Backend.DTO.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO userDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userDTO);
    void deleteUser(Long id);
    Optional<UserResponseDTO> getUserByNameUser(String userName);
}