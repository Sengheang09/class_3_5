package com.example.springsercurityclass35.controller;

import com.example.springsercurityclass35.dto.LoginRequestDto;
import com.example.springsercurityclass35.dto.LoginResponse;
import com.example.springsercurityclass35.dto.RegisterRequestDto;
import com.example.springsercurityclass35.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "AutdhController" , description = "register and login")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @Operation(summary = "Register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequestDto registerRequestDto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.registerUser(registerRequestDto));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.login(loginRequestDto));
    }
}
