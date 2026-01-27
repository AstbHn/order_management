package com.astb.order.domain.menu;

import lombok.Data;

@Data
public class Menu {
    // NULL값 판단을 위해 Integer 사용
    private Long menuId;
    private Long storeId;
    private String name;
    private Integer price;
    private boolean active = true;
}
