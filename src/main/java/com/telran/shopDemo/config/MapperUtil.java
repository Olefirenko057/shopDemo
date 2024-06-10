package com.telran.shopDemo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MapperUtil {
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public static <R,E>List<R> convertList(List<E> list, Function<E,R> function) {
        return list.stream().map(e -> function.apply(e)).collect(Collectors.toList());
    }
}
