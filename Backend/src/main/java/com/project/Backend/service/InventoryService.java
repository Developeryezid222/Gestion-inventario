package com.project.Backend.service;

import com.project.Backend.entity.Inventory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface InventoryService {
    List<Inventory> getAllInventories();
    Optional<Inventory> getInventoryById(Long id);
    Inventory saveInventory(Inventory inventory);
    void deleteById(Long id);

    void deleteById(Long productId, Long storeId);

    List<Inventory> getAllInventoriesByProducts(Long idProduct);
    List<Inventory> getAllInventoriesByStores(Long idStore);
    Optional<Inventory>getInventoryByProductAndStore(Long idProduct, Long idStore);

}
