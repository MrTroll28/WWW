package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @GetMapping("/orders")
    public String viewOrders(@RequestParam Long customerId, Model model) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        model.addAttribute("orders", orderService.getOrders(customer));
        model.addAttribute("customerId", customerId);
        return "orders";
    }
}