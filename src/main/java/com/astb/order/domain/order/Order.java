package com.astb.order.domain.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long orderId;
    private Long menuId;
    private String menuName;
    private Integer menuPrice;
    private Integer count;
    private Long totalPrice;
    private LocalDateTime orderTime;
}
