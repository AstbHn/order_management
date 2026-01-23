package com.astb.order.controller;

import com.astb.order.dto.SignupDTO;
import com.astb.order.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // final 선언을 위한 어노테이션
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    //비인증전용 컨트롤러
    @GetMapping("/")
    public String index(){
        return "auth/index";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }


    @PostMapping("/signup")
    public String signup(
            @Valid SignupDTO dto,
            BindingResult result
    ) {
        // 회원가입 시도
        if (result.hasErrors()) {
            return "auth/register";
        }

        userService.signup(dto);
        return "redirect:/auth/login";
    }

}
