package me.kn.ecommerce.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.model.Comment;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.repo.CommentRepository;
import me.kn.ecommerce.repo.CustomerRepository;
import me.kn.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;

    @PostMapping("/products/{id}/comment")
    public String addComment(@PathVariable Long id,
                             @RequestParam @NotBlank String content,
                             Principal principal,
                             Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepo.findByUser_Username(principal.getName())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Comment c = new Comment();
        c.setProduct(product);
        c.setCustomer(customer);
        c.setContent(content);
        c.setCreatedAt(LocalDateTime.now());
        commentRepo.save(c);

        return "redirect:/products/" + id;
    }
}
