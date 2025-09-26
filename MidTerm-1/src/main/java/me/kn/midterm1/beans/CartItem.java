package me.kn.midterm1.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kn.midterm1.model.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

    private Product product;

    private int quantity;

    public double getTotal(){
        return product.getPrice() * quantity;
    }
}
