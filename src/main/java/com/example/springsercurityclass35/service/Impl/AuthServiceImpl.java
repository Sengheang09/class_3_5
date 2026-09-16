package com.example.springsercurityclass35.service.Impl;


import com.example.springsercurityclass35.dto.Message;
import com.example.springsercurityclass35.dto.RegisterRequestDto;
import com.example.springsercurityclass35.entities.Role;
import com.example.springsercurityclass35.entities.User;
import com.example.springsercurityclass35.entities.enums.RoleType;
import com.example.springsercurityclass35.repo.RoleRepository;
import com.example.springsercurityclass35.repo.UserRepository;
import com.example.springsercurityclass35.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleResult;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Message registerUser(RegisterRequestDto requestDto) {

        userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("user name already exists"));

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("email already exists");
        }

        Role role = new Role();

        role = roleRepository.findByName(RoleType.USER);

        User user = new User();

        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        user.getRoles().add(role);


        return null;
    }
}
