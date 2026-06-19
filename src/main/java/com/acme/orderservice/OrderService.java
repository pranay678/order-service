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

    // BUG: discount() does not guard against null — intentional test-failure
    public double applyDiscount(Order order, Double discountPct) {
        // discountPct is allowed to be null by callers — NPE trigger
        double price = order.getQuantity() * 9.99;
        return price - (price * discountPct);    // NullPointerException when discountPct == null
    }
}
