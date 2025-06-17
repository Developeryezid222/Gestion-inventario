package com.project.Backend.service;

import com.project.Backend.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> getAllStores();
    Optional<Store> getStoreById(Long id);
    Store saveStore(Store store);

    void deleteStore(Long id);
    Optional<Store> getStoreByStoreName(String StoreName);
}
