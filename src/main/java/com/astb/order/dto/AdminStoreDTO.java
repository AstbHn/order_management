package com.astb.order.dto;

import lombok.Data;

@Data
public class AdminStoreDTO {
    private Long storeId;
    private String name;
    private String category;
    private String userId;
    private Long totalSales;
}
