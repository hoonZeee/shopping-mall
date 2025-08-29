package com.example.demo.controller.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpRequestDto {

    @Size(min = 5, max = 10)
    private String username;

    //대소문자 최소 1개씩, 8글자이상
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 대소문자 포함 8글자 이상이어야 합니다.")
    private String password;

    private String name;
}
