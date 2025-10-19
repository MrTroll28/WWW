package me.kn.ecommerce.repo;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import me.kn.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
           SELECT p FROM Product p
           WHERE lower(p.name) LIKE lower(concat('%', :q, '%'))
              OR lower(p.description) LIKE lower(concat('%', :q, '%'))
           """)
    List<Product> search(@Param("q") String q);
}
