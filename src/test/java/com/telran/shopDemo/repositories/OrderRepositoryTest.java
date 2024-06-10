package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.*;
import com.telran.shopDemo.entities.Order;
import com.telran.shopDemo.entities.Order;
import com.telran.shopDemo.entities.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    private final long ORDER_ID = 2;
    private final long USER_ID = 3;
    private Order order = new Order();
    @BeforeEach
    void setUp() {
        order.setUser(userRepository.findById(USER_ID).orElse(null));
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void get() {
        Order expectedOrder = orderRepository.findById(ORDER_ID).orElse(null);
        assertNotNull(expectedOrder);
        assertTrue(expectedOrder.getOrderId() > 0);
    }
    @Test
    void edit() {
        Order expectedOrder = orderRepository.findById(ORDER_ID).orElse(null);
        assertNotNull(expectedOrder);

        expectedOrder.setUser(userRepository.findById(2l).orElse(null));
        Order actualOrder = orderRepository.save(expectedOrder);
        assertNotNull(actualOrder);
        Assertions.assertEquals(expectedOrder.getUser(),actualOrder.getUser());
    }

    @Test
    void insert() {
        Order expectedOrder = order;
        Order actualOrder = orderRepository.save(expectedOrder);
        assertNotNull(actualOrder);
        Assertions.assertEquals(expectedOrder.getOrderId(),actualOrder.getOrderId());
    }
    @Test
    void delete() {
        Order actualOrder = orderRepository.save(order);
        assertNotNull(actualOrder);

        orderRepository.delete(order);
        actualOrder = orderRepository.findById(actualOrder.getOrderId()).orElse(null);
        assertNull(actualOrder);
    }
}