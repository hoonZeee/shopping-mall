package com.example.demo.service;

import com.example.demo.controller.user.dto.UserSignUpRequestDto;
import com.example.demo.controller.user.dto.UserSignUpResponseDto;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.repository.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserSignUpResponseDto signUp(UserSignUpRequestDto request){
        String encode = passwordEncoder.encode(request.getPassword());

        User user = User.create(
                request.getUsername(),
                encode,
                request.getName()
        );

        User created = userRepository.save(user);

        return UserSignUpResponseDto.from(created);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("존재하지않는 유저 - username : " + username));
    }

}
