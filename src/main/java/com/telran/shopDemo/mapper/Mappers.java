package com.telran.shopDemo.mapper;

import com.telran.shopDemo.dto.FavoriteDto;
import com.telran.shopDemo.dto.UserDto;
import com.telran.shopDemo.entities.Favorite;
import com.telran.shopDemo.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;
    public FavoriteDto convertIntoFavoriteDto(Favorite favorite) {
        if(favorite == null) {
            return null;
        }
        FavoriteDto map = modelMapper.map(favorite, FavoriteDto.class);
        return map;
    }
    public Favorite convertIntoFavorite(FavoriteDto favoriteDto) {
        if(favoriteDto == null) {
            return null;
        }
        Favorite map = modelMapper.map(favoriteDto, Favorite.class);
        map.setUser(convertIntoUser(favoriteDto.getUsers()));
        return map;
    }
    public UserDto convertIntoUserDto(User user) {
        if(user == null) {
            return null;
        }
        modelMapper.typeMap(User.class,UserDto.class)
                .addMappings(mapper -> mapper.skip(UserDto::setPasswordHash));
        UserDto map = modelMapper.map(user, UserDto.class);
        return map;
    }
    public User convertIntoUser(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
        User map = modelMapper.map(userDto, User.class);
        return map;
    }
}
