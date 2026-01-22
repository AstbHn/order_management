package com.astb.order.domain.store;

import lombok.Data;

@Data
public class Store {
    private String storeId;
    private String userId;
    private String name;
    private String category;
}
