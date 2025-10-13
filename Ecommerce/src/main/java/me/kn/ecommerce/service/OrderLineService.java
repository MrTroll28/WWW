package me.kn.ecommerce.service;

import me.kn.ecommerce.model.OrderLine;
import me.kn.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public Optional<OrderLine> findById(Long id) {
        return orderLineRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderLineRepository.deleteById(id);
    }
}
