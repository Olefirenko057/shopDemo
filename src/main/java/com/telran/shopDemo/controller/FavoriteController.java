package com.telran.shopDemo.controller;

import com.telran.shopDemo.dto.FavoriteDto;
import com.telran.shopDemo.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<FavoriteDto> getFavorites() {
        return favoriteService.getFavorites();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private FavoriteDto getFavoriteById(@PathVariable Long id) {
        return favoriteService.getFavoriteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteFavoriteById(@PathVariable Long id) {
       favoriteService.deleteFavoriteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    private FavoriteDto insertFavorites(@RequestBody FavoriteDto favoriteDto) {
        return favoriteService.insertFavorites(favoriteDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private FavoriteDto updateFavorites(@RequestBody FavoriteDto favoriteDto) {
        return favoriteService.updateFavorites(favoriteDto);
    }

}
