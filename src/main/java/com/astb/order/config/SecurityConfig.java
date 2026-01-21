package com.astb.order.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/css/**", "/js/**").permitAll() //비인증
                        //인증
                        .requestMatchers("/client/**").hasRole("CLIENT")
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // 로그인 설정
                .formLogin(login -> login
                        .loginPage("/auth/login")          // GET 로그인 페이지
                        .loginProcessingUrl("/auth/login") // POST 로그인 처리
                        .successHandler((request, response, authentication) -> {
                            // 로그인 데이터의 ROLE 가져오기
                            var roles = authentication.getAuthorities().toString();

                            // 각 ROLE에 따른 화면 분기
                            if (roles.contains("ROLE_ADMIN")) {
                                response.sendRedirect("/admin/home");
                            } else if (roles.contains("ROLE_SELLER")) {
                                response.sendRedirect("/seller/home");
                            } else {
                                response.sendRedirect("/client/home");
                            }
                        })
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
