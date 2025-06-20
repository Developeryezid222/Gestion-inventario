package com.project.Backend.service.Imp;

import com.project.Backend.entity.Store;
import com.project.Backend.repository.StoreRepository;
import com.project.Backend.service.StoreService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class StoreServiceImp implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImp(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store saveStore(Store store) {
            return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {

    }

    @Override
    public Optional<Store> getStoreByStoreName(String StoreName) {
        return storeRepository.findByStoreName(StoreName);
    }
}
