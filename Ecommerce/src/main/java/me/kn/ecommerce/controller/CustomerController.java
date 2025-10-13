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
    public String register(@ModelAttribute Customer customer, @ModelAttribute User user) {
        // Persist customer first
        Customer savedCustomer = customerService.save(customer);
        // Link user to customer and set default role
        user.setCustomer(savedCustomer);
        user.setRole("ROLE_USER");
        userService.register(user);
        return "redirect:/login";
    }
}