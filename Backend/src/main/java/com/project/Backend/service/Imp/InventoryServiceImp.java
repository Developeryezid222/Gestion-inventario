package com.project.Backend.service.Imp;


import com.project.Backend.entity.Inventory;
import com.project.Backend.entity.InventoryId;
import com.project.Backend.repository.InventoryRepository;
import com.project.Backend.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImp implements InventoryService {

    private final InventoryRepository inventoryRepository;


    public InventoryServiceImp(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(new InventoryId());
    }



    @Transactional
    @Override
    public Inventory saveInventory(Inventory inventory) {
            return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public void deleteById(Long productId, Long storeId) {
        InventoryId inventoryId = new InventoryId(productId, storeId);
        inventoryRepository.deleteById(inventoryId);
    }





    @Override
    public List<Inventory> getAllInventoriesByProducts(Long idProduct) {
        return inventoryRepository.findByProductIdProduct(idProduct);
    }

    @Override
    public List<Inventory> getAllInventoriesByStores(Long idStore) {
        return inventoryRepository.findByStoreIdStore(idStore);
    }

    @Override
    public Optional<Inventory> getInventoryByProductAndStore(Long idProduct, Long idStore) {
        return inventoryRepository.findByProductIdProductAndStoreIdStore(idProduct, idStore);
    }
}
