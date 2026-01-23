package com.astb.order.controller;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.store.Store;
import com.astb.order.domain.user.CustomUserDetails;
import com.astb.order.service.MenuService;
import com.astb.order.service.OrderService;
import com.astb.order.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final StoreService storeService;
    private final MenuService menuService;
    private final OrderService orderService;

    @GetMapping("/home")
    public String home() {
        return "client/home";
    }

    @GetMapping("/stores")
    public String storeList(@RequestParam String category, Model model) {

        List<Store> stores = storeService.findByCategory(category);

        model.addAttribute("category", category);
        model.addAttribute("stores", stores);

        return "client/storelist";
    }

    @GetMapping("/order")
    public String orderPage(@RequestParam Long storeId, Model model) {

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

        orderService.createOrder(
                user.getUsername(),
                storeId,
                menuIds,
                quantities
        );

        return "redirect:/client/home";
    }
}