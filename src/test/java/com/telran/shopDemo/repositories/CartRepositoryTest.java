package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    private final long CART_ID = 1;
    private final long USER_ID = 2;
    private Cart expectedCart = new Cart();

    @BeforeEach
    void setUp() {
        expectedCart.setUser(userRepository.findById(USER_ID).orElse(null));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        expectedCart.setCartId(CART_ID);
        Optional<Cart> actualCart = cartRepository.findById(expectedCart.getCartId());
        assertNotNull(actualCart.get());
        Assertions.assertEquals(expectedCart.getCartId(),actualCart.get().getCartId());
    }

    @Test
    void insert() {
        Cart expectedCart2 = cartRepository.save(expectedCart);
        Cart actualCart = cartRepository.findById(expectedCart2.getCartId()).orElse(null);
        assertEquals(expectedCart2.getCartId(),actualCart.getCartId());
        assertEquals(expectedCart2.getUser(),actualCart.getUser());
    }

    @Test
    void edit() {
        Cart expectedCart2 = cartRepository.findById(CART_ID).orElse(null);
        assertNotNull(expectedCart2);

        expectedCart2.setUser(userRepository.findById(1l).orElse(null));
        Cart actualCart = cartRepository.save(expectedCart2);
        Assertions.assertEquals(expectedCart2.getUser(),actualCart.getUser());
        Assertions.assertEquals(expectedCart2.getCartId(),actualCart.getCartId());
    }

    @Test
    void delete() {
         cartRepository.save(expectedCart);
         Cart expectedCart2 = cartRepository.findById(expectedCart.getCartId()).orElse(null);
         assertNotNull(expectedCart2);

         cartRepository.delete(expectedCart);
         expectedCart2 = cartRepository.findById(expectedCart.getCartId()).orElse(null);
         assertNull(expectedCart2);
    }
}