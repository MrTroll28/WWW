package me.kn.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.kn.ecommerce.model.*;
import me.kn.ecommerce.repo.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Tạo đơn hàng mới dựa vào giỏ hàng trong session.
     * - Sao chép từng CartItem thành OrderLine.
     * - Gắn Customer, thời gian đặt hàng.
     * - Tính tổng giá trị đơn.
     */
    @Transactional
    public Order placeOrder(Customer customer, Cart cart) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalStateException("Giỏ hàng trống, không thể tạo đơn hàng.");
        }

        Order order = new Order();
        order.setCustomer(customer);

        // Nếu entity Order của bạn có trường orderDate
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;

        // Duyệt các sản phẩm trong giỏ (Map<Long, CartItem>)
        for (CartItem item : cart.getItemsList()) {
            OrderLine line = new OrderLine();
            line.setOrder(order);
            line.setProduct(item.getProduct());
            line.setQuantity(item.getQuantity());
            line.setPrice(item.getProduct().getPrice());

            // thêm dòng vào danh sách OrderLine của Order
            order.getOrderLines().add(line);
        }
        return orderRepo.save(order);
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
