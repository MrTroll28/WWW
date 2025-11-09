package me.kn.ecommerce.controller;

import jakarta.servlet.http.HttpSession;
import me.kn.ecommerce.model.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import me.kn.ecommerce.model.Cart;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.service.ProductService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                      HttpSession session,
                      RedirectAttributes redirectAttributes) {

        Product p = productService.findById(productId).orElseThrow();

        Cart cart = getCart(session);
        int added = cart.add(p, qty);

        if (added == 0) {
            redirectAttributes.addFlashAttribute("error",
                    "Sản phẩm '" + p.getName() + "' hiện đã hết hàng!");
        } else if (added < qty) {
            redirectAttributes.addFlashAttribute("warning",
                    "Chỉ thêm được " + added + " sản phẩm vì lượng tồn kho có hạn!");
        } else {
            redirectAttributes.addFlashAttribute("success",
                    "Đã thêm " + added + " sản phẩm '" + p.getName() + "' vào giỏ.");
        }

        return "redirect:/cart/view";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long productId, HttpSession session) {
        getCart(session).remove(productId);
        return "redirect:/cart/view";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long productId,
                         @RequestParam int quantity,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {

        Cart cart = getCart(session);

        if (!cart.getItems().containsKey(productId)) {
            redirectAttributes.addFlashAttribute("error", "Mặt hàng không tồn tại trong giỏ!");
            return "redirect:/cart/view";
        }

        CartItem item = cart.getItems().get(productId);
        Product p = item.getProduct();
        int stock = p.getStock();

        if (quantity <= 0) {
            cart.remove(productId);
            redirectAttributes.addFlashAttribute("success",
                    "Đã xóa '" + p.getName() + "' khỏi giỏ.");
            return "redirect:/cart/view";
        }

        if (quantity > stock) {
            item.setQuantity(stock);
            redirectAttributes.addFlashAttribute("warning",
                    "Chỉ còn " + stock + " sản phẩm '" + p.getName() + "'. Đã tự điều chỉnh.");
        } else {
            item.setQuantity(quantity);
            redirectAttributes.addFlashAttribute("success",
                    "Đã cập nhật '" + p.getName() + "' thành " + quantity + " sản phẩm.");
        }

        return "redirect:/cart/view";
    }
}
