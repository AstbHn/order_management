package com.astb.order.domain.store;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    void insertStore(Store store);
    List<Store> findByUserId(String userId);
    List<Store> findByCategory(String category);
    Store findById(Long storeId);
}
