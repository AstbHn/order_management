package com.astb.order.domain.history;

import lombok.Data;

@Data
public class History {
    private Long historyId;
    private Long orderId;
    private String storeId;
    private String userId;
}
