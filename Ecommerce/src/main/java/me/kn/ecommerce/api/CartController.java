package me.kn.ecommerce.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import me.kn.ecommerce.model.Cart;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.service.ProductService;

import java.util.Collections;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    private Cart getCart(HttpSession session) {
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) {
            c = new Cart();
            session.setAttribute("cart", c);
        }
        return c;
    }

    @GetMapping("/view")
    public String view(HttpSession session, Model model) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", new java.util.ArrayList<>(cart.getItems().values()));
        return "cart/view";
    }




    @PostMapping("/add")
    public String add(@RequestParam Long productId,
                      @RequestParam(defaultValue = "1") int qty,
                      HttpSession session) {
        Product p = productService.findById(productId).orElseThrow();
        getCart(session).add(p, qty);
        return "redirect:/cart/view";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long productId, HttpSession session) {
        getCart(session).remove(productId);
        return "redirect:/cart/view";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
        Cart cart = getCart(session);
        if (cart.getItems() != null && cart.getItems().containsKey(productId)) {
            if (quantity <= 0) cart.remove(productId);
            else cart.getItems().get(productId).setQuantity(quantity);
        }
        return "redirect:/cart/view";
    }
}
