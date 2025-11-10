package me.kn.ecommerce.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.repo.ProductRepository;
import me.kn.ecommerce.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductRepository productRepository;

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Product> products = (q != null && !q.isBlank())
                ? service.search(q)
                : service.listAll();

        model.addAttribute("products", products);
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("title", "Danh sách sản phẩm");

        return "products/list";
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        model.addAttribute("p", p);
        model.addAttribute("comments",
                (p.getComments() != null) ? p.getComments() : List.of());
        model.addAttribute("title", p.getName());

        return "products/detail";
    }

    // ==========================
    // 🔐 CRUD cho ADMIN
    // ==========================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/new")
    public String createForm(Model model) {
        model.addAttribute("p", new Product());
        model.addAttribute( "title", "Thêm sản phẩm mới");
        return "products/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/save")
    public String save(
            @Valid @ModelAttribute("p") Product p,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("title", p.getId() == null ? "Thêm sản phẩm" : "Sửa sản phẩm");
            return "products/form";
        }

        service.save(p);
        return "redirect:/products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = service.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        model.addAttribute("p", product);
        model.addAttribute("title", "Chỉnh sửa sản phẩm");
        return "products/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}
