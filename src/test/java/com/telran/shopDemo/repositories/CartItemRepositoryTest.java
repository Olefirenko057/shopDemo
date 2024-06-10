package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.Cart;
import com.telran.shopDemo.entities.CartItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    CartItemRepository itemRepository;
    CartItem cartItemsExpected = new CartItem();

    @Autowired
    private CartRepository cartRepository;
    Cart cart = new Cart();

    private final long CART_ID = 2L;
    private final long CARTITEMS_ID = 1L;
    @BeforeEach
    void setUp() {
        cartItemsExpected.setCart(cartRepository.findById(CART_ID).orElse(null));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInsert() {
        itemRepository.save(cartItemsExpected);
        CartItem cartItemsActual = itemRepository.findById(cartItemsExpected.getCartItemId()).orElse(null);
        assertNotNull(cartItemsActual);
        Assertions.assertEquals(cartItemsExpected.getCartItemId(),cartItemsActual.getCartItemId());
        Assertions.assertEquals(cartItemsExpected.getCart(),cartItemsActual.getCart());
        Assertions.assertTrue(cartItemsActual.getCartItemId() > 0);
    }

    @Test
    void get() {
        cartItemsExpected.setCartItemId(CARTITEMS_ID);
        Optional<CartItem> cartItemsActual = itemRepository.findById(cartItemsExpected.getCartItemId());
        Assertions.assertEquals(cartItemsExpected.getCartItemId(),cartItemsActual.get().getCartItemId());
        assertTrue(cartItemsActual.get().getCartItemId()>0);
    }

    @Test
    void updateQuantitySuccessfully() {
        CartItem cartItemsTested = itemRepository.findById(CARTITEMS_ID).orElseThrow();
        int originalQuantity = cartItemsTested.getQuantity();
        cartItemsTested.setQuantity(10);
        itemRepository.save(cartItemsTested);

        CartItem cartItemsActual = itemRepository.findById(CARTITEMS_ID).orElseThrow();
        Assertions.assertEquals(10, cartItemsActual.getQuantity());
        Assertions.assertNotEquals(originalQuantity, cartItemsActual.getQuantity());
    }

    @Test
    void delete() {
        itemRepository.save(cartItemsExpected);
        CartItem test = itemRepository.findById(cartItemsExpected.getCartItemId()).orElse(null);
        assertNotNull(test);

        itemRepository.delete(cartItemsExpected);
        test = itemRepository.findById(cartItemsExpected.getCartItemId()).orElse(null);
        assertNull(test);
    }

}