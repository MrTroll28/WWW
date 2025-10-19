package me.kn.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Product product;
    private int quantity;

    /** Tổng tiền của sản phẩm này */
    public double getLineTotal() {
        if (product == null) return 0.0;
        return product.getPrice() * quantity;
    }
}
