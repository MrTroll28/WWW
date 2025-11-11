    package me.kn.ecommerce.model.dto;

    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Pattern;
    import lombok.Data;

    @Data
    public class RegisterFormDTO {

        @NotBlank(message = "Tên không được trống")
        private String fullName;

        @NotBlank(message = "Tên đăng nhập không được trống")
        private String username;

        @NotBlank(message = "Mật khẩu không được trống")
        private String password;

        @NotBlank(message = "Email không được để trống")
        @Email(message = "Email không hợp lệ")
        private String email;

        @NotBlank(message = "Số điện thoại không được để trống")
        @Pattern(
                regexp = "^(0[3|5|7|8|9])[0-9]{8}$",
                message = "Số điện thoại không hợp lệ"
        )
        private String phone;

        @NotBlank(message = "Địa chỉ không được để trống")
        private String address;
    }
