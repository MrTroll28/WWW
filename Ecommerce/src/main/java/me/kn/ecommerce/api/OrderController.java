package me.kn.ecommerce.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.model.Cart;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.Order;
import me.kn.ecommerce.repo.CustomerRepository;
import me.kn.ecommerce.repo.UserRepository;
import me.kn.ecommerce.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;

    @GetMapping("/list")
    public String listOrders(Principal principal, Model model) {
        if (principal == null) return "redirect:/auth/login";

        // Lấy user trực tiếp từ username
        var userOpt = userRepo.findByUsername(principal.getName());
        if (userOpt.isEmpty()) return "redirect:/auth/login";

        var user = userOpt.get();
        boolean isAdmin = user.getRole().equalsIgnoreCase("ADMIN");

        List<Order> orders = isAdmin
                ? orderService.findAll()
                : orderService.findByCustomerUsername(principal.getName());

        model.addAttribute("orders", orders);
        model.addAttribute("title", "Danh sách đơn hàng");
        model.addAttribute("content", "order/list");
        return "_layout";
    }


    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/auth/login";
        }

        // Tìm user theo username
        var userOpt = userRepo.findByUsername(principal.getName());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        var user = userOpt.get();
        boolean isAdmin = user.getRole().equalsIgnoreCase("ADMIN");

        // Lấy đơn hàng
        Order order = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        // Nếu không phải admin, kiểm tra xem order có thuộc về customer không
        if (!isAdmin) {
            var customer = customerRepo.findByUser_Username(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            if (!order.getCustomer().getId().equals(customer.getId())) {
                throw new RuntimeException("Bạn không có quyền xem đơn hàng này");
            }
        }

        model.addAttribute("order", order);
        model.addAttribute("title", "Chi tiết đơn hàng");
        model.addAttribute("content", "order/detail");
        return "_layout";
    }


    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("title", "Thanh toán");
        model.addAttribute("content", "order/checkout");
        return "_layout";
    }

    // ✅ Đặt hàng
    @PostMapping("/checkout")
    public String placeOrder(HttpSession session, Principal principal, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart/view";
        }

        Customer customer = customerRepo.findByUser_Username(principal.getName())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        orderService.placeOrder(customer, cart);
        session.removeAttribute("cart");

        model.addAttribute("title", "Đặt hàng thành công");
        model.addAttribute("content", "order/summary");
        return "_layout";
    }
}
