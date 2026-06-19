package com.acme.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder_shouldPersistAndReturnOrder() {
        Order order = orderService.create("Widget", 3);
        assertThat(order.getId()).isNotNull();
        assertThat(order.getProduct()).isEqualTo("Widget");
        assertThat(order.getQuantity()).isEqualTo(3);
    }

    @Test
    void applyDiscount_shouldCalculateCorrectTotal() {
        Order order = orderService.create("Gadget", 2);
        double result = orderService.applyDiscount(order, 0.10);
        assertThat(result).isEqualTo(17.982);
    }

    // This test passes null intentionally — reproduces the NullPointerException bug
    @Test
    void applyDiscount_withNullDiscount_shouldThrowMeaningfulError() {
        Order order = orderService.create("Gadget", 2);
        // BUG: applyDiscount does not guard null — will throw NullPointerException
        double result = orderService.applyDiscount(order, null);
        // We expect 0 discount, but this line will never be reached
        assertThat(result).isEqualTo(19.98);
    }
}
