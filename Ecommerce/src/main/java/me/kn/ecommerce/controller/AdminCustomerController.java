package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customers")
@RequiredArgsConstructor
public class AdminCustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String listCustomers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Customer> customers = customerService.search(keyword);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        return "admin/customers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customers/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        model.addAttribute("customer", customer);
        return "admin/customers/form";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        model.addAttribute("customer", customer);
        model.addAttribute("orders", customer.getOrders());
        return "admin/customers/detail";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/admin/customers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:/admin/customers";
    }
}
