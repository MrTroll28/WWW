package me.kn.ecommerce.controller;

import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.Order;
import me.kn.ecommerce.model.OrderLine;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.OrderLineService;
import me.kn.ecommerce.service.OrderService;
import me.kn.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderLineService orderLineService;

    @GetMapping
    public String listOrders(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Order> orders = orderService.search(keyword);
        model.addAttribute("orders", orders);
        model.addAttribute("keyword", keyword);
        return "admin/orders/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Order order = new Order();
        order.setCustomer(new Customer());
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.findAll());
        return "admin/orders/form";
    }

    @PostMapping
    public String createOrder(@RequestParam Long customerId,
                              @RequestParam(defaultValue = "PENDING") String status) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(status);
        order.setOrderDate(LocalDateTime.now());
        Order saved = orderService.save(order);
        return "redirect:/admin/orders/" + saved.getId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        model.addAttribute("order", order);
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("products", productService.findAll());
        return "admin/orders/detail";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable Long id,
                              @RequestParam String status) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        orderService.save(order);
        return "redirect:/admin/orders/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }

    @PostMapping("/{orderId}/lines")
    public String addLine(@PathVariable Long orderId,
                          @RequestParam Long productId,
                          @RequestParam int quantity,
                          @RequestParam(required = false) Double price) {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        OrderLine line = new OrderLine();
        line.setOrder(order);
        line.setProduct(product);
        line.setQuantity(quantity);
        line.setPrice(price != null ? price : product.getPrice());
        orderLineService.save(line);
        return "redirect:/admin/orders/" + orderId;
    }

    @GetMapping("/{orderId}/lines/{lineId}/edit")
    public String editLine(@PathVariable Long orderId,
                           @PathVariable Long lineId,
                           Model model) {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        OrderLine line = orderLineService.findById(lineId)
                .orElseThrow(() -> new IllegalArgumentException("Order line not found"));
        model.addAttribute("order", order);
        model.addAttribute("orderLine", line);
        model.addAttribute("products", productService.findAll());
        return "admin/orders/line-form";
    }

    @PostMapping("/{orderId}/lines/{lineId}/update")
    public String updateLine(@PathVariable Long orderId,
                             @PathVariable Long lineId,
                             @RequestParam Long productId,
                             @RequestParam int quantity,
                             @RequestParam Double price) {
        OrderLine line = orderLineService.findById(lineId)
                .orElseThrow(() -> new IllegalArgumentException("Order line not found"));
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        line.setProduct(product);
        line.setQuantity(quantity);
        line.setPrice(price);
        orderLineService.save(line);
        return "redirect:/admin/orders/" + orderId;
    }

    @PostMapping("/{orderId}/lines/{lineId}/delete")
    public String deleteLine(@PathVariable Long orderId,
                             @PathVariable Long lineId) {
        orderLineService.deleteById(lineId);
        return "redirect:/admin/orders/" + orderId;
    }
}
