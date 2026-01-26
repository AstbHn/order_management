package com.astb.order.service;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.menu.MenuMapper;
import com.astb.order.domain.order.Order;
import com.astb.order.domain.order.OrderMapper;
import com.astb.order.domain.orderitem.OrderItem;
import com.astb.order.domain.orderitem.OrderItemMapper;
import com.astb.order.dto.OrderListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final MenuMapper menuMapper;

    //주문등록
    public void createOrder(String userId,
                            Long storeId,
                            List<Long> menuIds,
                            List<Integer> quantities) {
        //주문 넣은 상품
        List<OrderItem> items = new ArrayList<>();

        for (int i = 0; i < menuIds.size(); i++) {
            int qty = quantities.get(i);
            if (qty <= 0) continue;

            Menu menu = menuMapper.findById(menuIds.get(i));

            OrderItem item = new OrderItem();
            item.setMenuId(menu.getMenuId());
            item.setMenuName(menu.getName());
            item.setMenuPrice(menu.getPrice());
            item.setQuantity(qty);
            item.setTotalPrice((long) menu.getPrice() * qty);

            items.add(item);
        }

        if (items.isEmpty()) {
            throw new IllegalArgumentException("주문할 메뉴가 없습니다.");
        }

        long totalPrice = items.stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setStoreId(storeId);
        order.setTotalPrice(totalPrice);

        // 주문 생성
        orderMapper.insertOrder(order);

        // 주문 상세 생성
        for (OrderItem item : items) {
            item.setOrderId(order.getOrderId());
            orderItemMapper.insertOrderItem(item);
        }
    }

    public List<OrderListDTO> findByUserId(String userId) {
        return orderMapper.findByUserId(userId);
    }

    public Order findById(Long orderId) {
        return orderMapper.findById(orderId);
    }
}
