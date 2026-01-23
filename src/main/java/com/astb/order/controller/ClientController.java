package com.astb.order.controller;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.order.Order;
import com.astb.order.domain.orderitem.OrderItem;
import com.astb.order.domain.store.Store;
import com.astb.order.domain.user.CustomUserDetails;
import com.astb.order.service.MenuService;
import com.astb.order.service.OrderItemService;
import com.astb.order.service.OrderService;
import com.astb.order.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    //고객 컨트롤러
    private final StoreService storeService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/home")
    public String home() {
        return "client/home";
    }

    @GetMapping("/stores")
    public String storeList(@RequestParam String category, Model model) {
        //선택한 카테고리에 포함된 가게 리스트
        List<Store> stores = storeService.findByCategory(category);

        model.addAttribute("category", category);
        model.addAttribute("stores", stores);

        return "client/storeList";
    }

    /* === 주문 === */
    @GetMapping("/order")
    public String orderPage(@RequestParam Long storeId, Model model) {
        //가게 아이디를 사용한 가게 및 메뉴 조회
        Store store = storeService.findById(storeId);
        List<Menu> menus = menuService.findByStoreId(storeId);

        model.addAttribute("store", store);
        model.addAttribute("menus", menus);

        return "client/order";
    }

    @PostMapping("/order")
    public String createOrder(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam Long storeId,
            @RequestParam List<Long> menuIds,
            @RequestParam List<Integer> quantities
    ) {
        //주문
        orderService.createOrder(
                user.getUsername(),
                storeId,
                menuIds,
                quantities
        );

        return "redirect:/client/home";
    }

    @GetMapping("/orderList")
    public String orderList(@AuthenticationPrincipal CustomUserDetails user, Model model){
        String userId = user.getUsername();

        List<Order> orders = orderService.findByUserId(userId);

        model.addAttribute("orders", orders);
        return "client/orderList";
    }

    @GetMapping("/order/{orderId}")
    public String orderDetail(@PathVariable Long orderId,
                              Model model) {

        Order order = orderService.findById(orderId);
        List<OrderItem> items = orderItemService.findByOrderId(orderId);

        model.addAttribute("order", order);
        model.addAttribute("items", items);

        return "client/orderDetail";
    }
}