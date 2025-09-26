package me.kn.midterm1.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.kn.midterm1.model.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Cart {

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void add(Product product) {
        CartItem existedItem = items
                .stream()
                .filter(x -> x.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (existedItem != null) {
            existedItem.setQuantity(existedItem.getQuantity() + 1);
        } else {
            items.add(new CartItem(product, 1));
        }
    }

    public void remove(long productId) {
        items.removeIf(x -> x.getProduct().getId() == productId);
    }

    public void update(long productId, int quantity) {
        CartItem existedItem = items
                .stream()
                .filter(x -> x.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (quantity <= 0) {
            remove(productId);
            return;
        }

        if (existedItem != null) {
            existedItem.setQuantity(quantity);
        }
    }

    public double getTotal() {
        return items
                .stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }
}
