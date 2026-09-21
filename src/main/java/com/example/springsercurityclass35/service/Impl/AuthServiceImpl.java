package com.example.springsercurityclass35.service.Impl;


import com.example.springsercurityclass35.dto.LoginRequestDto;
import com.example.springsercurityclass35.dto.LoginResponse;
import com.example.springsercurityclass35.dto.Message;
import com.example.springsercurityclass35.dto.RegisterRequestDto;
import com.example.springsercurityclass35.entities.Role;
import com.example.springsercurityclass35.entities.User;
import com.example.springsercurityclass35.entities.enums.RoleType;
import com.example.springsercurityclass35.exception.BadRequestException;
import com.example.springsercurityclass35.exception.ResourceNotFoundException;
import com.example.springsercurityclass35.repo.RoleRepository;
import com.example.springsercurityclass35.repo.UserRepository;
import com.example.springsercurityclass35.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.management.relation.RoleResult;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public SecretKey signingKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Message registerUser(RegisterRequestDto requestDto) {

        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new BadRequestException("user name already exist.");
        }

        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("email already exists");
        }


        Role role = roleRepository.findByName(RoleType.USER);

        User user = new User();

        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        user.getRoles().add(role);

        userRepository.save(user);

        return new Message("success.");
    }

    @Override
    public LoginResponse login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("username not found with username: " + requestDto.getUsername())
        );

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword()
                    )
            );
        }catch (Exception e){
            throw new BadCredentialsException("Invalid username or password." + e.getMessage());

        }

        String token = Jwts.builder()
                .subject(requestDto.getUsername())
                .claim("Roles" ,
                        user.getRoles().
                        stream()
                        .map(role -> role.getName().name()).toList()

                )
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signingKey())
                .compact();

        return new LoginResponse(
                user.getUsername(),
                token
        );
    }

}
