package iuh.fit.dangkhoinguyen.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartBean {
    private List<CartItemBean> items;
    public CartBean() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItemBean(product, 1));
    }

    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
                if (quantity <= 0) {
                    removeProduct(productId);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItemBean item : items) {
            total += item.getSubTotal();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
