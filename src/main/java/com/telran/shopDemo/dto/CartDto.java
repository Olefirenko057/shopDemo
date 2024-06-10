package com.telran.shopDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private long cartId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("users")
    private UserDto userDto;

}
