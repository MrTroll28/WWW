package me.kn.ecommerce.api;

import me.kn.ecommerce.model.Cart;
import jakarta.servlet.http.HttpSession;
import me.kn.ecommerce.model.CartItem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @GetMapping
    public Cart viewCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @PostMapping("/add")
    public Cart addItem(@RequestParam Long productId,
                        @RequestParam(defaultValue = "1") int quantity,
                        HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addItem(new CartItem(productId, "Product " + productId, 100.0, quantity));
        session.setAttribute("cart", cart);
        return cart;
    }

    @DeleteMapping("/remove/{id}")
    public Cart removeItem(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) cart.removeItem(id);
        return cart;
    }
}
