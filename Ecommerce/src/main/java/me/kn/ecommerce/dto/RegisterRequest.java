package me.kn.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Tên đăng nhập không được để trống") String username,
        @NotBlank(message = "Mật khẩu không được để trống") String password,
        @NotBlank(message = "Họ không được để trống") String firstName,
        @NotBlank(message = "Tên không được để trống") String lastName,
        @Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String email,
        String phone,
        String address
) {
}