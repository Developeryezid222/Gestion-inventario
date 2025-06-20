package com.project.Backend.controller;


import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.entity.Store;
import com.project.Backend.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Almacen")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getIdByStore(@PathVariable Long id) {
        return storeService.getStoreById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("Almacen no encontrado con el id: " + id));
    }


    @PostMapping("/save")
    public ResponseEntity<Store> createStore(@Valid @RequestBody Store store) {
        if(storeService.getStoreByStoreName(store.getNameStore()).isPresent()) {
            throw new IllegalArgumentException("El nombre del almacen ya existe");
        }
        Store newStore = storeService.saveStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStore);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @Valid @RequestBody Store store) {
        store.setIdStore(id);
        Store updatedStore = storeService.saveStore(store);
        return ResponseEntity.ok(updatedStore);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Store> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
