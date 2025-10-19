package me.kn.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import me.kn.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
