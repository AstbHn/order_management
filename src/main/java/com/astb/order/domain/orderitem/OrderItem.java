package com.astb.order.domain.orderitem;

import lombok.Data;

@Data
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long menuId;
    private String menuName;
    private int menuPrice;
    private int quantity;
    private long totalPrice;
}

