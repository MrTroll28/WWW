package me.kn.ecommerce.repository;

import me.kn.ecommerce.model.Order;
import me.kn.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);

    List<Order> findByCustomer_EmailContainingIgnoreCaseOrStatusContainingIgnoreCase(String email, String status);

    List<Order> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(String firstName, String lastName);
}