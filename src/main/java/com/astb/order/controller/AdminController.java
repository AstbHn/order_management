package com.astb.order.controller;

import com.astb.order.domain.user.User;
import com.astb.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
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
    public String stores(){
        //가게정보 가져오기
        return "admin/stores";
    }

    @GetMapping("/stores/menu")
    public String menu(){
        //가게 아이디 이용하여 해당 메뉴 가져오기 수정 X 활성 비활성만 가능
        return "admin/stores/menu";
    }

    @GetMapping("/order")
    public String order(){
        //전체 주문 내역 가져오기
        return "admin/orderList";
    }
}
