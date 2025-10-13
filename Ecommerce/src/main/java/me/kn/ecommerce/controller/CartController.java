package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Cart;
import me.kn.ecommerce.model.Customer;
import jakarta.servlet.http.HttpSession;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/cart")
    public String viewCart(@RequestParam(required = false) Long customerId,
                           HttpSession session,
                           Model model) {
        // Retrieve or initialize the Cart object in the session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("customerId", customerId);
        return "cart";
    }

    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam(required = false) Long customerId,
                                 @RequestParam Long productId,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.updateQuantity(productId, quantity);
            session.setAttribute("cart", cart);
        }
        return customerId == null ? "redirect:/cart" : "redirect:/cart?customerId=" + customerId;
    }

    @PostMapping("/cart/remove")
    public String removeItem(@RequestParam(required = false) Long customerId,
                             @RequestParam Long productId,
                             HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeItem(productId);
            session.setAttribute("cart", cart);
        }
        return customerId == null ? "redirect:/cart" : "redirect:/cart?customerId=" + customerId;
    }

    @GetMapping("/cart/clear")
    public String clearCart(@RequestParam(required = false) Long customerId,
                            HttpSession session) {
        session.removeAttribute("cart");
        return customerId == null ? "redirect:/cart" : "redirect:/cart?customerId=" + customerId;
    }

    @PostMapping("/cart/checkout")
    public String checkout(@RequestParam Long customerId,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Cart cart = (Cart) session.getAttribute("cart");
        try {
            if (cart == null || cart.isEmpty()) {
                throw new IllegalStateException("Cart is empty");
            }
            orderService.checkoutFromSession(customer, cart);
            // Clear the session cart after successful checkout
            session.removeAttribute("cart");
            redirectAttributes.addFlashAttribute("message", "Order placed successfully");
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/cart?customerId=" + customerId;
        }
        return "redirect:/orders?customerId=" + customerId;
    }

    private Cart getSessionCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}