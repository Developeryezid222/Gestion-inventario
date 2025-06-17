package com.project.Backend.controller;



import com.project.Backend.Exception.ResourceNotFoundException;
import com.project.Backend.entity.Inventory;
import com.project.Backend.service.InventoryService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/Inventario")
public class InventoryController {


    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories(){
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getAllByIdInventory(@PathVariable Long id ) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con el id" + id));
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        Inventory created = inventoryService.saveInventory(inventory);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT: Actualizar inventario existente
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> saveInventory( @Valid @RequestBody Inventory inventoryDetails) {
        Inventory updated = inventoryService.saveInventory( inventoryDetails);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Eliminar inventario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

