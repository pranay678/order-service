package com.acme.orderservice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order create(String product, int quantity) {
        return repository.save(new Order(product, quantity));
    }

    // FIXME: discountPct can be null when called from PromotionService —
    // unboxing null Double to double throws NullPointerException
    public double applyDiscount(Order order, Double discountPct) {
        double price = order.getQuantity() * 9.99;
        return price - (price * discountPct);  // NPE when discountPct == null
    }
}
