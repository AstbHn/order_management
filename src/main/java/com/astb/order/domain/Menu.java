package com.astb.order.domain;

import lombok.Data;

@Data
public class Menu {
    // NULL값 판단을 위해 Integer 사용
    private Integer menuId;
    private String storeId;
    private String name;
    private Integer price;
}
