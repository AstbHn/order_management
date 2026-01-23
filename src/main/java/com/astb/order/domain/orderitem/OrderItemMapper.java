package com.astb.order.domain.orderitem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    void insertOrderItem(OrderItem orderItem);
    List<OrderItem> findByOrderId(Long orderId);
}
