package com.astb.order.service;

import com.astb.order.domain.orderitem.OrderItem;
import com.astb.order.domain.orderitem.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;

    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }
}

