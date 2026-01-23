package com.astb.order.domain.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long orderId;
    private String userId;   // 주문한 고객
    private Long storeId;    // 가게
    private long totalPrice;
    private LocalDateTime orderTime;
}

