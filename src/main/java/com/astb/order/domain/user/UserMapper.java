package com.astb.order.domain.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUserId(String userId);
    void insertUser(User user);
    boolean existsByRole(String role);
    List<User> selectUsers();
}
