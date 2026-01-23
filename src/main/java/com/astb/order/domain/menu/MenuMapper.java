package com.astb.order.domain.menu;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    void insertMenu(Menu menu);
}
