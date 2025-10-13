package me.kn.ecommerce.service;

import me.kn.ecommerce.model.*;
import me.kn.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String trimmed = keyword.trim();
        Set<Order> matches = new LinkedHashSet<>();
        try {
            Long orderId = Long.parseLong(trimmed);
            orderRepository.findById(orderId).ifPresent(matches::add);
        } catch (NumberFormatException ignored) {
            // Ignore non-numeric values when searching by ID
        }
        matches.addAll(orderRepository
                .findByCustomer_EmailContainingIgnoreCaseOrStatusContainingIgnoreCase(trimmed, trimmed));
        matches.addAll(orderRepository
                .findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(trimmed, trimmed));
        return new ArrayList<>(matches);
    }
}