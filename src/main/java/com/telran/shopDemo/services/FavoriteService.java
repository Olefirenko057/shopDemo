package com.telran.shopDemo.services;

import com.telran.shopDemo.config.MapperUtil;
import com.telran.shopDemo.dto.FavoriteDto;
import com.telran.shopDemo.entities.Favorite;
import com.telran.shopDemo.mapper.Mappers;
import com.telran.shopDemo.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final Mappers mappers;
    public List<FavoriteDto> getFavorites() {
        return MapperUtil.convertList(favoriteRepository.findAll(),mappers::convertIntoFavoriteDto);
    }

    public FavoriteDto getFavoriteById(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElse(null);
        return mappers.convertIntoFavoriteDto(favorite);
    }

    public void deleteFavoriteById(Long id) {
       favoriteRepository.findById(id).ifPresent(favoriteRepository::delete);
    }

    public FavoriteDto insertFavorites(FavoriteDto favoriteDto) {
        if(favoriteDto.getFavoriteId() <= 0) {
            return null;
        }
        Favorite favorite = mappers.convertIntoFavorite(favoriteDto);
        favorite = favoriteRepository.save(favorite);
        return mappers.convertIntoFavoriteDto(favorite);
    }

    public FavoriteDto updateFavorites(FavoriteDto favoriteDto) {
        if(favoriteDto.getFavoriteId() <= 0) {
            return null;
        }
        Optional<Favorite> temp = favoriteRepository.findById(favoriteDto.getFavoriteId());
        if(!temp.isPresent()) {
            return null;
        }
        Favorite favorite = mappers.convertIntoFavorite(favoriteDto);
        return mappers.convertIntoFavoriteDto(favoriteRepository.save(favorite));
    }
}
