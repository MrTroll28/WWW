package me.kn.ecommerce.model;

import lombok.Data;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Cart {

    private Map<Long, CartItem> items = new LinkedHashMap<>();

    public void addItem(CartItem item) {
        if (items.containsKey(item.getProductId())) {
            CartItem existing = items.get(item.getProductId());
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            items.put(item.getProductId(), item);
        }
    }

    public void updateQuantity(Long productId, int quantity) {
        if (!items.containsKey(productId)) {
            return;
        }
        if (quantity <= 0) {
            items.remove(productId);
        } else {
            items.get(productId).setQuantity(quantity);
        }
    }

    public void removeItem(Long productId) {
        items.remove(productId);
    }

    public double getTotalPrice() {
        return items.values().stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Collection<CartItem> getAllItems() {
        return items.values();
    }
}