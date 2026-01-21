package com.astb.order.domain;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String role;      // CLIENT / SELLER / ADMIN
    private String address;
}
