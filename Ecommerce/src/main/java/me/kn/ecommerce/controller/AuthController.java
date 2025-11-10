package me.kn.ecommerce.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import me.kn.ecommerce.model.dto.RegisterFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import me.kn.ecommerce.service.AuthService;

@Controller
/**
 * Mà là bạn vô tình bật Method Validation bằng @Validated trên Controller.
 * Khi đó validation không đi qua BindingResult → mà ném exception ngay.
 */
//@Validated  Cái lỗi khốn nạn này
@RequestMapping("/auth")
public class AuthController {

    private final AuthService auth;
    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    // Trang đăng nhập
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("content", "auth/login");
        return "_auth-layout";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("form", new RegisterFormDTO());
        model.addAttribute("content", "auth/register");
        return "_auth-layout";
    }

    @PostMapping("/register")
    public String handleRegister(
            @Valid @ModelAttribute("form") RegisterFormDTO form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("content", "auth/register");
            return "_auth-layout";
        }

        try {
            auth.registerCustomer(
                    form.getUsername(),
                    form.getPassword(),
                    form.getFullName(),
                    form.getEmail(),
                    form.getPhone(),
                    form.getAddress()
            );

            return "redirect:/auth/login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("content", "auth/register");
            return "_auth-layout";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/auth/login?logout";
    }
}
