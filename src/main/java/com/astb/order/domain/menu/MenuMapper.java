package com.astb.order.domain.menu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    void insertMenu(Menu menu);
    Menu findById(Long menuId);
    List<Menu> findByStoreId(Long storeId);
    int update(
            @Param("menuId") Long menuId,
            @Param("name") String name,
            @Param("price") Integer price
    );

    // 메뉴 삭제 (비활성화)
    int delete(@Param("menuId") Long menuId);

    int active(@Param("menuId") Long menuId);
}
