package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.User;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
        public String register(@ModelAttribute("customer") Customer customer,
                @ModelAttribute("user") User user,
                Model model,
                RedirectAttributes redirectAttributes) {
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại.");
            model.addAttribute("customer", customer);
            model.addAttribute("user", user);
            return "register";
        }

        if (customer.getEmail() != null && customerService.emailExists(customer.getEmail())) {
            model.addAttribute("errorMessage", "Email đã được sử dụng.");
            model.addAttribute("customer", customer);
            model.addAttribute("user", user);
            return "register";
        }

        Customer savedCustomer = customerService.save(customer);
        user.setCustomer(savedCustomer);
        user.setRole("ROLE_USER");
        userService.register(user);

        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công. Vui lòng đăng nhập.");
        return "redirect:/login";
    }
}
