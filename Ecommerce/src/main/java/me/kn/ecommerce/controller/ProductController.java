package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Comment;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.Product;
import jakarta.servlet.http.HttpSession;
import me.kn.ecommerce.model.Cart;
import me.kn.ecommerce.model.CartItem;
import me.kn.ecommerce.service.CommentService;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CommentService commentService;
    private final CustomerService customerService;
    @GetMapping({"/", "/products"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("comments", product.getComments());
        return "product-detail";
    }

    @PostMapping("/products/{id}/comment")
    public String addComment(@PathVariable Long id,
                             @RequestParam Long customerId,
                             @RequestParam String content,
                             RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setCustomer(customer);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        commentService.save(comment);
        redirectAttributes.addFlashAttribute("message", "Comment added successfully");
        return "redirect:/products/" + id;
    }

    @PostMapping("/products/{id}/addToCart")
    public String addToCart(@PathVariable Long id,
                            @RequestParam Long customerId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        // Ensure the customer exists; the ID is used only for redirect purposes.
        customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        // Fetch the product being added
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // Retrieve or create the cart stored in the session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        // Create a cart item representing the product and quantity
        CartItem item = new CartItem(product.getId(), product.getName(), product.getPrice(), quantity);
        // Add the item to the cart (existing quantities are incremented automatically)
        cart.addItem(item);
        // Persist the cart back into the session
        session.setAttribute("cart", cart);
        redirectAttributes.addFlashAttribute("message", "Product added to cart");
        return "redirect:/cart?customerId=" + customerId;
    }
}