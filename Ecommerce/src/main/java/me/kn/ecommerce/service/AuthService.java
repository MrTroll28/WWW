package me.kn.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.User;
import me.kn.ecommerce.repository.CustomerRepository;
import me.kn.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void register(Customer customer, String username, String password) {
        User user = User.builder().username(username)
                .password(passwordEncoder.encode(password))
                .role("CUSTOMER").build();
        userRepository.save(user);
        customer.setUser(user);
        customerRepository.save(customer);
    }
    public User findByUsername(String username) { return userRepository.findByUsername(username); }
}
