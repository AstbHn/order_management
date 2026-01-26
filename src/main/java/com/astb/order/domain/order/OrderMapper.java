package com.astb.order.domain.order;

import com.astb.order.dto.OrderListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(Order order);
    List<OrderListDTO> findByUserId(String userId);
    Order findById(Long orderId);
}
