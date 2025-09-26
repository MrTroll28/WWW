package me.kn.midterm1.dao;

import me.kn.midterm1.model.Product;

public class ProductDAO extends GenericDAO<Product> {

    public ProductDAO() {
        super(Product.class);
    }
}
