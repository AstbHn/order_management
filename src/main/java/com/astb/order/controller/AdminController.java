package com.astb.order.controller;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.order.Order;
import com.astb.order.domain.store.Store;
import com.astb.order.domain.user.User;
import com.astb.order.dto.AdminStoreDTO;
import com.astb.order.dto.OrderListDTO;
import com.astb.order.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    private final StoreService storeService;
    private final MenuService menuService;

    //관리자 전용 컨트롤러
    @GetMapping("/home")
    public String home(){
        return "admin/home";
    }

    @GetMapping("/users")
    public String users(Model model){
        //유저 정보 가져오기
        List<User> userList = userService.selectUsers();
        model.addAttribute("userList", userList);

        return "admin/users";
    }

    @GetMapping("/stores")
    public String storeList(Model model) {
        List<AdminStoreDTO> storeList = storeService.allStoreSales();
        model.addAttribute("storeList", storeList);
        return "admin/storeList";
    }

    @GetMapping("/menus")
    public String menus(@RequestParam Long storeId, Model model) {
        // 해당 가게 메뉴 조회
        List<Menu> menuList = menuService.findByStoreId(storeId);
        model.addAttribute("menuList", menuList);
        return "admin/menus";
    }
    @PostMapping("/update")
    public String updateMenu(
            @RequestParam Long storeId,
            @RequestParam Long menuId
    ) {
        menuService.deleteMenu(menuId);
        return "redirect:/admin/menus?storeId=" + storeId;
    }

    @PostMapping("/enable")
    public String updateUserEnabled(
            @RequestParam String userId,
            @RequestParam boolean enabled
    ) {
        adminService.updateUserEnabled(userId, enabled);
        return "redirect:/admin/users";
    }
}
