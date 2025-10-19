package me.kn.ecommerce.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.repo.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository r) { this.repo = r; }

    public List<Product> listAll() { return repo.findAll(); }
    public List<Product> search(String q) { return repo.search(q == null ? "" : q); }
    public Optional<Product> findById(Long id) { return repo.findById(id); }

    @Transactional public Product save(Product p) { return repo.save(p); }
    @Transactional public void delete(Long id) { repo.deleteById(id); }
}
