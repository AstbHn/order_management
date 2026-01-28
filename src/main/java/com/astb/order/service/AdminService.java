package com.astb.order.service;

import com.astb.order.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserMapper userMapper;

    public void updateUserEnabled(String userId, boolean enabled) {
        userMapper.updateEnabled(userId, enabled);
    }
}
