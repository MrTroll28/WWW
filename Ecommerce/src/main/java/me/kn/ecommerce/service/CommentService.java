package me.kn.ecommerce.service;

import me.kn.ecommerce.model.Comment;
import me.kn.ecommerce.model.Product;
import me.kn.ecommerce.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findByProductId(Long productId) {
        return commentRepository.findByProduct(
                Product.builder().id(productId).build());
    }
}