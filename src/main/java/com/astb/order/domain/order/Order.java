package com.astb.order.domain.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer orderId;
    private Integer menuId;
    private String menuName;
    private Integer menuPrice;
    private Integer count;
    private Integer totalPrice;
    private LocalDateTime orderTime;
}
