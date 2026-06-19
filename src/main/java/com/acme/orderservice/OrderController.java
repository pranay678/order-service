package com.acme.orderservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> list() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestParam String product,
                                        @RequestParam int quantity) {
        return ResponseEntity.ok(service.create(product, quantity));
    }
}
