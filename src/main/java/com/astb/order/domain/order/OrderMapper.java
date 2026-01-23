package com.astb.order.domain.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    List<Order> findByUserId(String userId);
    Order findById(Long orderId);
}
