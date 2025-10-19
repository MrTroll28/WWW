package me.kn.ecommerce.api;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import me.kn.ecommerce.service.AuthService;

@Controller
@Validated
@RequestMapping("/auth")
public class AuthController {

    private final AuthService auth;
    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    // Trang đăng nhập
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // Model form đăng ký
    public static class RegisterForm {
        @NotBlank public String username;
        @NotBlank public String password;
        @NotBlank public String fullName;

        public String getUsername() { return username; }
        public void setUsername(String v) { this.username = v; }
        public String getPassword() { return password; }
        public void setPassword(String v) { this.password = v; }
        public String getFullName() { return fullName; }
        public void setFullName(String v) { this.fullName = v; }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("form") RegisterForm form, Model model) {
        try {
            auth.registerCustomer(form.username, form.password, form.fullName);
            model.addAttribute("msg", "Đăng ký thành công, vui lòng đăng nhập!");
            return "auth/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/auth/login?logout";
    }
}
