package com.project.Backend.repository;

import com.project.Backend.entity.Inventory;
import com.project.Backend.entity.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    List<Inventory> findByProductIdProduct(Long idProduct);
    List<Inventory> findByStoreIdStore(Long idStore);
    Optional<Inventory> findByProductIdProductAndStoreIdStore(Long idProduct, Long idStore);
}
