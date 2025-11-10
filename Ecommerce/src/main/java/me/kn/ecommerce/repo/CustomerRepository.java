package me.kn.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import me.kn.ecommerce.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUser_Username(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
