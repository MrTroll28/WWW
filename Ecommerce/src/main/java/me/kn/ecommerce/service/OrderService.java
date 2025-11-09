package me.kn.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.kn.ecommerce.model.*;
import me.kn.ecommerce.repo.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    @Transactional
    public void placeOrder(Customer customer, Cart cart) {
        for (var item : cart.getItems().values()) {
            Product p = item.getProduct();             // Product có trường stock
            int stock = p.getStock();

            if (item.getQuantity() > stock) {
                throw new RuntimeException(
                        "Sản phẩm '" + p.getName() +
                                "' chỉ còn " + stock + " sản phẩm!"
                );
            }
        }

        for (var item : cart.getItems().values()) {
            Product p = item.getProduct();
            p.setStock(p.getStock() - item.getQuantity());
            productRepo.save(p);                       // productRepo hỗ trợ save
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderLine> orderLines = new ArrayList<>();

        for (var item : cart.getItems().values()) {

            OrderLine line = new OrderLine();
            line.setOrder(order);
            line.setProduct(item.getProduct());
            line.setQuantity(item.getQuantity());
            line.setPrice(item.getProduct().getPrice());

            orderLines.add(line);
        }
        order.setOrderLines(orderLines);
        orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public List<Order> findByCustomerUsername(String username) {
        return orderRepo.findByCustomer_User_Username(username);
    }

    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

}
