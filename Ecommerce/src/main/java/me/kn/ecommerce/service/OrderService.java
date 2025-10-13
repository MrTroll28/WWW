package me.kn.ecommerce.service;

import me.kn.ecommerce.model.*;
import me.kn.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public Order checkoutFromSession(Customer customer, Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        for (CartItem item : cart.getAllItems()) {
            Long productId = item.getProductId();
            Product product = productService.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
            productService.decreaseStock(product, item.getQuantity());
            OrderLine line = new OrderLine();
            line.setOrder(order);
            line.setProduct(product);
            line.setQuantity(item.getQuantity());
            line.setPrice(item.getPrice());
            order.getOrderLines().add(line);
        }
        return orderRepository.save(order);
    }

    public List<Order> getOrders(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }
}