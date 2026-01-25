package com.astb.order.dto;

import lombok.Data;

@Data
public class SellerSalesDTO {

    // 가게 정보
    private int storeId;
    private String storeName;
    private long totalSales;

    // 메뉴 통계
    private String menuName;
    private int saleCount;
    private long saleAmount;

}
