package com.project.Backend;

import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.controller.StoreController;
import com.project.Backend.entity.Store;
import com.project.Backend.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StoreControllerTest {

    @Mock
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    private Store store;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        store = new Store();
        store.setIdStore(1L);
        store.setNameStore("Almacen 1");
    }

    @Test
    void testGetAllStores() {
        // Arrange
        List<Store> stores = Arrays.asList(store);
        when(storeService.getAllStores()).thenReturn(stores);

        // Act
        ResponseEntity<List<Store>> response = storeController.getAllStores();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stores, response.getBody());
        verify(storeService, times(1)).getAllStores();
    }

    @Test
    void testGetStoreById_Success() {
        // Arrange
        when(storeService.getStoreById(1L)).thenReturn(Optional.of(store));

        // Act
        ResponseEntity<Store> response = storeController.getIdByStore(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(store, response.getBody());
        verify(storeService, times(1)).getStoreById(1L);
    }

    @Test
    void testGetStoreByI() {
        // Arrange
        when(storeService.getStoreById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> storeController.getIdByStore(1L));
        assertTrue(exception.getMessage().contains("Almacen no encontrado con el id:"));
        verify(storeService, times(1)).getStoreById(1L);
    }

    @Test
    void testCreateStore_Success() {
        // Arrange
        when(storeService.getStoreByStoreName("Almacen 1")).thenReturn(Optional.empty());
        when(storeService.saveStore(any(Store.class))).thenReturn(store);

        // Act
        ResponseEntity<Store> response = storeController.createStore(store);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(store, response.getBody());
        verify(storeService, times(1)).getStoreByStoreName("Almacen 1");
        verify(storeService, times(1)).saveStore(any(Store.class));
    }

    @Test
    void testCreateStore_StoreNameExists() {
        // Arrange
        when(storeService.getStoreByStoreName("Almacen 1")).thenReturn(Optional.of(store));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            storeController.createStore(store);
        });
        assertEquals("El nombre del almacen ya existe", exception.getMessage());
        verify(storeService, times(1)).getStoreByStoreName("Almacen 1");
        verify(storeService, never()).saveStore(any(Store.class));
    }

    @Test
    void testUpdateStore_Success() {
        // Arrange
        when(storeService.saveStore(any(Store.class))).thenReturn(store);

        // Act
        ResponseEntity<Store> response = storeController.updateStore(1L, store);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(store, response.getBody());
        assertEquals(1L, store.getIdStore());
        verify(storeService, times(1)).saveStore(any(Store.class));
    }

    @Test
    void testDeleteStore_Success() {
        // Arrange
        doNothing().when(storeService).deleteStore(anyLong());

        // Act
        ResponseEntity<Store> response = storeController.deleteStore(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(storeService, times(1)).deleteStore(1L);
    }
}