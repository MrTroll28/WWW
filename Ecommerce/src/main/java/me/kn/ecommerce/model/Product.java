package me.kn.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Product entity represents an item that can be purchased.  It includes
 * descriptive attributes such as name, description and price as well as
 * inventory count. Products have a one‑to‑many relationship with order lines
 * (each product can appear on multiple orders) and with comments (customers
 * can leave feedback for a product).
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Pattern(
            regexp = "^[A-Z][A-Za-z]{1,}$",
            message = "Tên sản phẩm phải bắt đầu là chữ in hoa, ít nhất là 2 ký tự"
    )
    private String name;

    @Column(length = 2000)
    @NotBlank(message = "Mô tả sản phẩm không được để trống")
    private String description;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải > 0")
    private double price;

    @Min(value = 0, message = "Tồn kho không được < 0")
    private int stock;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Transient
    private List<CartItem> cartItems;
}