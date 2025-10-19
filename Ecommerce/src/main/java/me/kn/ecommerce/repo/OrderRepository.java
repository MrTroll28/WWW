package me.kn.ecommerce.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import me.kn.ecommerce.model.Order;
import me.kn.ecommerce.model.Customer;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCustomer_User_Username(String username);
}
