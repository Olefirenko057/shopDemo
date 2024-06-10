package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.*;
import com.telran.shopDemo.entities.User;
import com.telran.shopDemo.entities.User;
import com.telran.shopDemo.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user = new User();
    private final long USER_ID = 3;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        User expectedUser = userRepository.findById(USER_ID).orElse(null);
        assertNotNull(expectedUser);
        assertTrue(expectedUser.getUserId() > 0);
    }
    @Test
    void edit() {
        User expectedUser = userRepository.findById(USER_ID).orElse(null);
        assertNotNull(expectedUser);

        expectedUser.setName("Tom");
        User actualUser = userRepository.save(expectedUser);
        assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser.getName(),actualUser.getName());
    }
    @Test
    void insert() {
        User expectedUser = user;
        User actualUser = userRepository.save(expectedUser);
        assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser.getUserId(),actualUser.getUserId());
    }
    @Test
    void delete() {
        User actualUser = userRepository.save(user);
        assertNotNull(actualUser);

        userRepository.delete(user);
        actualUser = userRepository.findById(actualUser.getUserId()).orElse(null);
        assertNull(actualUser);
    }
}