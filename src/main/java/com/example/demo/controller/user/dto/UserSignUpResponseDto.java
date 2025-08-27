package com.example.demo.controller.user.dto;

import com.example.demo.repository.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpResponseDto {
    private Integer id;
    private String username;
    private String name;

    public static UserSignUpResponseDto from(User entity){
        return new UserSignUpResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getName()
        );
    }
}
