package me.kn.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long productId;

    private String productName;

    private double price;

    private int quantity;

    public double getTotal() {
        return price * quantity;
    }
}