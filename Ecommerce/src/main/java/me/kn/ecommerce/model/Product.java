package me.kn.ecommerce.model;

import jakarta.persistence.*;
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

    private String name;

    @Column(length = 2000)
    private String description;

    private double price;

    private int stock;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Transient
    private List<CartItem> cartItems;
}