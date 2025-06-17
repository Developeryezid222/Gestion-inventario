package com.project.Backend.service.Imp;

import com.project.Backend.DTO.UserRequestDTO;
import com.project.Backend.DTO.UserResponseDTO;

import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.entity.User;
import com.project.Backend.repository.UserRepository;
import com.project.Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        userRepository.findByUsername(userDTO.getUsername()).ifPresent(u -> {
            throw new IllegalArgumentException("El nombre de usuario ya existe.");
        });

        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user.setRol(userDTO.getRol());
        user.setCreationDate(LocalDateTime.now());

        return mapToDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("El nombre de usuario ya está en uso por otro usuario.");
                });



        existingUser.setUserName(userDTO.getUsername());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        }

        existingUser.setRol(userDTO.getRol());


        return mapToDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        userRepository.delete(user);
    }

    @Override
    public Optional<UserResponseDTO> getUserByNameUser(String userName) {
        return userRepository.findByUsername(userName)
                .map(this::mapToDTO);
    }

    // Mapper DTO (puedes moverlo a un mapper separado si prefieres)
    private UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getIdUser(),
                user.getUserName(),
                user.getRol(),
                user.getCreationDate(),
                user.getLastSession()
        );
    }
}
