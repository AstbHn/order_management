package com.astb.order.controller;

import com.astb.order.dto.SignupDTO;
import com.astb.order.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(
            @RequestParam(required = false) String error,
            Model model
    ) {
        if ("disabled".equals(error)) {
            model.addAttribute("errorMsg", "비활성화된 계정입니다. 관리자에게 문의하세요.");
        } else if (error != null) {
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }

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
