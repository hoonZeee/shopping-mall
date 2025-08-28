package com.example.demo.controller.user;

import com.example.demo.controller.user.dto.UserSignUpRequestDto;
import com.example.demo.controller.user.dto.UserSignUpResponseDto;
import com.example.demo.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signup(@Valid @RequestBody UserSignUpRequestDto request){
        return ResponseEntity.ok(userService.signUp(request));
    }
}
