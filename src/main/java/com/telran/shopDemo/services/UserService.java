package com.telran.shopDemo.services;

import com.telran.shopDemo.config.MapperUtil;
import com.telran.shopDemo.dto.UserDto;
import com.telran.shopDemo.entities.User;
import com.telran.shopDemo.mapper.Mappers;
import com.telran.shopDemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository repository;
   private final Mappers mappers;

    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return MapperUtil.convertList(users,mappers::convertIntoUserDto);
    }

    public UserDto getUserById(Long id) {
        return mappers.convertIntoUserDto(repository.findById(id).get());
    }
    public void deleteUserById(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    public UserDto insertUser(UserDto userDto) {
        User user = mappers.convertIntoUser(userDto);
        if (user == null) {
            return null;
        }
        return mappers.convertIntoUserDto(repository.save(user));
    }

    public UserDto updateUser(UserDto userDto) {
        User user = mappers.convertIntoUser(userDto);
        if (user == null || user.getUserId() <= 0 || !repository.findById(user.getUserId()).isPresent()) {
            throw new RuntimeException();
        }

        return mappers.convertIntoUserDto(repository.save(user));
    }
}
