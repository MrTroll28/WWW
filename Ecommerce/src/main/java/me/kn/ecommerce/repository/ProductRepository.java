package me.kn.ecommerce.repository;

import me.kn.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    java.util.List<Product> findByNameContainingIgnoreCase(String keyword);
}