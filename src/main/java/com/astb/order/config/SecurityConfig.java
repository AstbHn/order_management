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
                        .loginProcessingUrl("/auth/loginProc") // POST 로그인 처리
                        .successHandler((request, response, authentication) -> {
                            var roles = authentication.getAuthorities().toString();

                            if (roles.contains("ROLE_ADMIN")) {
                                response.sendRedirect("/admin/home");
                            } else if (roles.contains("ROLE_SELLER")) {
                                response.sendRedirect("/seller/home");
                            } else {
                                response.sendRedirect("/client/home");
                            }
                        })
                        .failureHandler((request, response, exception) -> {
                            if (exception instanceof org.springframework.security.authentication.DisabledException) {
                                response.sendRedirect("/auth/login?error=disabled");
                            } else {
                                response.sendRedirect("/auth/login?error=true");
                            }
                        })
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
