package com.astb.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderListDTO {
    private Long orderId;
    private String userId;   // 주문한 고객
    private Long storeId;    // 가게
    private String storeName;
    private long totalPrice;
    private LocalDateTime orderTime;
}
