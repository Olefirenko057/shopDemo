package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.OrderItem;
import com.telran.shopDemo.entities.OrderItem;
import com.telran.shopDemo.entities.OrderItem;
import com.telran.shopDemo.entities.OrderItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    private final long ORDER_ITEM_ID = 2;
    private final long ORDER_ID = 1;
    private OrderItem orderItem = new OrderItem();
    @BeforeEach
    void setUp() {
        orderItem.setOrder(orderRepository.findById(ORDER_ID).orElse(null));
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void get() {
        OrderItem expectedOrderItem = orderItemRepository.findById(ORDER_ITEM_ID).orElse(null);
        assertNotNull(expectedOrderItem);
        assertTrue(expectedOrderItem.getOrderItemId() > 0);
    }
    @Test
    void edit() {
        OrderItem expectedOrderItem = orderItemRepository.findById(ORDER_ITEM_ID).orElse(null);
        assertNotNull(expectedOrderItem);

        expectedOrderItem.setOrder(orderRepository.findById(2l).orElse(null));
        OrderItem actualOrderItem = orderItemRepository.save(expectedOrderItem);
        assertNotNull(actualOrderItem);
        Assertions.assertEquals(expectedOrderItem.getOrder(),actualOrderItem.getOrder());
    }

    @Test
    void insert() {
        OrderItem expectedOrderItem = orderItem;
        OrderItem actualOrderItem = orderItemRepository.save(expectedOrderItem);
        assertNotNull(actualOrderItem);
        Assertions.assertEquals(expectedOrderItem.getOrderItemId(),actualOrderItem.getOrderItemId());
    }

    @Test
    void delete() {
        OrderItem actualOrderItem = orderItemRepository.save(orderItem);
        assertNotNull(actualOrderItem);

        orderItemRepository.delete(orderItem);
        actualOrderItem = orderItemRepository.findById(actualOrderItem.getOrderItemId()).orElse(null);
        assertNull(actualOrderItem);
    }
}