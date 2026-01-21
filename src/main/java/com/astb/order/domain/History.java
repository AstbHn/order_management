package com.astb.order.domain;

import lombok.Data;

@Data
public class History {
    private Integer historyId;
    private Integer orderId;
    private String storeId;
    private String userId;
}
