package me.kn.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.User;
import me.kn.ecommerce.repo.CustomerRepository;
import me.kn.ecommerce.repo.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final CustomerRepository customerRepo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository u, CustomerRepository c, PasswordEncoder e) {
        this.userRepo = u; this.customerRepo = c; this.encoder = e;
    }

    @Transactional
    public User registerCustomer(String username, String rawPassword, String fullName) {
        if (userRepo.existsByUsername(username)) throw new IllegalArgumentException("Username already exists");
        Customer customer = new Customer();
        customer.setName(fullName);
        customerRepo.save(customer);

        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole("CUSTOMER");
        u.setCustomer(customer);
        return userRepo.save(u);
    }
}
