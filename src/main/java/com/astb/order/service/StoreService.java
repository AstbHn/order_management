package com.astb.order.service;

import com.astb.order.domain.store.Store;
import com.astb.order.domain.store.StoreMapper;
import com.astb.order.dto.AdminStoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    public void registerStore(Store store) {
        //가게 등록
        storeMapper.insertStore(store);
    }

    public List<Store> findByUserId(String userId) {
        //유저 아이디를 이용한 가게 조회
        return storeMapper.findByUserId(userId);
    }

    public List<Store> findByCategory(String category) {
        //카테고리를 이용한 가게 조회
        return storeMapper.findByCategory(category);
    }

    public Store findById(Long storeId) {
        //가게 아이디를 이용한 가게 조회
        return storeMapper.findById(storeId);
    }

    public List<AdminStoreDTO> allStoreSales() {
        return storeMapper.allStoreSales();
    }
}

