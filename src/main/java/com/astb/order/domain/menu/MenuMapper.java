package com.astb.order.domain.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    void insertMenu(Menu menu);
    Menu findById(Long menuId);
    List<Menu> findByStoreId(Long storeId);
}
