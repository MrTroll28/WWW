package me.kn.ecommerce.util;

import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.model.User;
import me.kn.ecommerce.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("Admin@123")); // ✅ mã hóa
            admin.setRole("ADMIN");
            userRepo.save(admin);
            System.out.println("Tạo tài khoản admin: admin / Admin@123");
        }
    }
}