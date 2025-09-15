package iuh.fit.dangkhoinguyen.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemBean implements Serializable {
    private Product product;
    private int quantity;

    public double getSubTotal() {
        return product.getPrice() * quantity;
    }
}
