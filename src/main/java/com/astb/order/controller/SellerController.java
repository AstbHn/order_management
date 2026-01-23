package com.astb.order.controller;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.store.Store;
import com.astb.order.domain.user.CustomUserDetails;
import com.astb.order.service.MenuService;
import com.astb.order.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    //판매자 전용 컨트롤러
    private final StoreService storeService;
    private final MenuService menuService;

    @GetMapping("/home")
    public String home(Model model,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {

        String userId = userDetails.getUsername();

        List<Store> storeList = storeService.findByUserId(userId);

        model.addAttribute("hasStore", !storeList.isEmpty());
        model.addAttribute("storeList", storeList);

        return "seller/home";
    }

    @GetMapping("/register")
    public String register(){
        return "seller/register";
    }

    @PostMapping("/register")
    public String register(Store store,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 로그인한 점주 아이디
        String loginId = userDetails.getUsername();
        store.setUserId(loginId);

        storeService.registerStore(store);

        return "redirect:/seller/home";
    }

    @GetMapping("/menu/register")
    public String menuRegisterForm(@RequestParam Long storeId, Model model) {

        model.addAttribute("storeId", storeId);
        return "seller/menu/register";
    }
    @PostMapping("/menu/register")
    public String menuRegister(Menu menu) {
        menuService.registerMenu(menu);
        return "redirect:/seller/home";
    }
}
