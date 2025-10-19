package me.kn.ecommerce.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.kn.ecommerce.dto.AuthResponse;
import me.kn.ecommerce.dto.LoginRequest;
import me.kn.ecommerce.dto.RegisterRequest;
import me.kn.ecommerce.model.Customer;
import me.kn.ecommerce.model.User;
import me.kn.ecommerce.service.CustomerService;
import me.kn.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;
    private final CustomerService customerService;

    @PostMapping("/login")
            public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
                User user = userService.findByUsername(request.username());
                if (user == null || !userService.passwordMatches(request.password(), user.getPassword())) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new AuthResponse(null, null, null, "Tên đăng nhập hoặc mật khẩu không đúng."));
                }

                return ResponseEntity.ok(new AuthResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        "Đăng nhập thành công."
                ));
            }

            @PostMapping("/register")
            public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
                if (userService.usernameExists(request.username())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new AuthResponse(null, null, null, "Tên đăng nhập đã tồn tại."));
                }

                if (customerService.emailExists(request.email())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new AuthResponse(null, null, null, "Email đã được sử dụng."));
                }

                Customer customer = Customer.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .email(request.email())
                        .phone(request.phone())
                        .address(request.address())
                        .build();

                Customer savedCustomer = customerService.save(customer);

                User user = User.builder()
                        .username(request.username())
                        .password(request.password())
                        .role("ROLE_USER")
                        .customer(savedCustomer)
                        .build();

                User savedUser = userService.register(user);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new AuthResponse(
                                savedUser.getId(),
                                savedUser.getUsername(),
                                savedUser.getRole(),
                                "Đăng ký thành công."
                        ));
            }
        }