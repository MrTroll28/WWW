package me.kn.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Product entity represents an item that can be purchased.  It includes
 * descriptive attributes such as name, description and price as well as
 * inventory count.  Products have a one‑to‑many relationship with order lines
 * (each product can appear on multiple orders) and with comments
 * (customers can leave feedback for a product)【877306590398801†L100-L109】.
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

    /**
     * Order lines referencing this product.  We use mappedBy on the product field
     * in OrderLine.  Lazy loading is used because order lines are rarely needed
     * when simply listing products.  No cascade is defined here because removing
     * a product should not automatically remove associated orders (business
     * records).
     */
    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();

    /**
     * Comments associated with this product.  CascadeType.ALL and orphanRemoval
     * are used so that comments are persisted and removed along with the product.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    @Transient
    private List<CartItem> cartItems;
}