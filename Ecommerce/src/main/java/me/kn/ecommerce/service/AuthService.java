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
    public User registerCustomer(
            String username,
            String rawPassword,
            String fullName,
            String email,
            String phone,
            String address
    ) {

        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }

        if (customerRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        if (customerRepo.existsByPhone(phone)) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }

        Customer customer = new Customer();
        customer.setName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerRepo.save(customer);

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(rawPassword));
        user.setRole("CUSTOMER");
        user.setCustomer(customer);

        return userRepo.save(user);
    }
}
