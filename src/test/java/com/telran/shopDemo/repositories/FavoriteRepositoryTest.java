package com.telran.shopDemo.repositories;

import com.telran.shopDemo.entities.Favorite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FavoriteRepositoryTest {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    private final long FAVORITE_ID = 2;
    private final long USER_ID = 1l;
    private Favorite testFavorite = new Favorite();

    @BeforeEach
    void setUp() {
        testFavorite.setUser(userRepository.findById(USER_ID).orElse(null));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        Favorite expectedFavorite = favoriteRepository.findById(FAVORITE_ID).orElse(null);
        assertNotNull(expectedFavorite);
        assertTrue(expectedFavorite.getFavoriteId() > 0);
    }

    @Test
    void edit() {
        Favorite expectedFavorite = favoriteRepository.findById(FAVORITE_ID).orElse(null);
        assertNotNull(expectedFavorite);

        expectedFavorite.setUser(userRepository.findById(2l).orElse(null));
        Favorite actualFavorite = favoriteRepository.save(expectedFavorite);
        assertNotNull(actualFavorite);
        Assertions.assertEquals(expectedFavorite.getUser(),actualFavorite.getUser());
    }

    @Test
    void insert() {
        Favorite expectedFavorite = testFavorite;
        Favorite actualFavorite = favoriteRepository.save(expectedFavorite);
        assertNotNull(actualFavorite);
        Assertions.assertEquals(expectedFavorite.getFavoriteId(),actualFavorite.getFavoriteId());
    }

    @Test
    void delete() {
        Favorite actualFavorite = favoriteRepository.save(testFavorite);
        assertNotNull(actualFavorite);

        favoriteRepository.delete(testFavorite);
        actualFavorite = favoriteRepository.findById(actualFavorite.getFavoriteId()).orElse(null);
        assertNull(actualFavorite);
    }

}