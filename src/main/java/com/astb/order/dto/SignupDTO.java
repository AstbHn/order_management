package com.astb.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class SignupDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    private String address;

    @NotBlank
    private String role;

    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isPasswordMatched() {
        return password != null
                && password.equals(passwordCheck);
    }
}
