package com.astb.order.config;

import com.astb.order.domain.user.User;
import com.astb.order.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInitConfig implements ApplicationRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userService.existsByRole("ADMIN")) {
            return;
        }

        String rawPassword = UUID.randomUUID().toString().substring(0, 10);
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User admin = new User();
        admin.setUserId("admin");
        admin.setPassword(encodedPassword);
        admin.setRole("ADMIN");

        userService.signUpAdmin(admin);

        log.warn("======================================");
        log.warn("ID  : admin");
        log.warn("PWD : {}", rawPassword);
        log.warn("======================================");
    }
}