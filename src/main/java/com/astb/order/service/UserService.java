package com.astb.order.service;

import com.astb.order.domain.user.*;
import com.astb.order.dto.SignupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByRole(String role) {
        return userMapper.existsByRole(role);
    }

    public void signUpAdmin(User user) {
        userMapper.insertUser(user);
    }
    //회원가입
    public void signup(SignupDTO dto) {

        User user = new User();
        user.setUserId(dto.getUserId());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAddress(dto.getAddress());
        user.setRole(dto.getRole());

        userMapper.insertUser(user);
    }

    public List<User> selectUsers(){
        return userMapper.selectUsers();
    }
    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    // 아이디 중복 체크
    @Transactional(readOnly = true)
    public boolean existsByUserId(String userId) {
        return userMapper.findByUserId(userId) != null;
    }


}

