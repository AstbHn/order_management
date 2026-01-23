package com.astb.order.service;

import com.astb.order.domain.store.Store;
import com.astb.order.domain.store.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    public void registerStore(Store store) {
        storeMapper.insertStore(store);
    }

    public List<Store> findByUserId(String userId) {
        return storeMapper.findByUserId(userId);
    }

    public List<Store> findByCategory(String category) {
        return storeMapper.findByCategory(category);
    }

    public Store findById(Long storeId) {
        return storeMapper.findById(storeId);
    }

}

