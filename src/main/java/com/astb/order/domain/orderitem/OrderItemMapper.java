package com.astb.order.domain.orderitem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    void insertOrderItem(OrderItem orderItem);
}
