package me.kn.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    // Map: key = productId, value = CartItem
    private Map<Long, CartItem> items = new HashMap<>();

    /** Thêm sản phẩm vào giỏ hàng */
    public void add(Product product, int quantity) {
        if (quantity <= 0) quantity = 1;

        CartItem existing = items.get(product.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(product.getId(), new CartItem(product, quantity));
        }
    }

    /** Xoá sản phẩm khỏi giỏ hàng */
    public void remove(Long productId) {
        items.remove(productId);
    }

    /** Tính tổng tiền */
    public double getTotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getLineTotal)
                .sum();
    }

    /** Trả về danh sách CartItem để dễ duyệt bằng foreach */
    public Collection<CartItem> getItemsList() {
        return items.values();
    }

    /** Kiểm tra giỏ hàng rỗng */
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
