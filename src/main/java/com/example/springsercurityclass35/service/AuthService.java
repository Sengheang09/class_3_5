package com.example.springsercurityclass35.service;

import com.example.springsercurityclass35.dto.Message;
import com.example.springsercurityclass35.dto.RegisterRequestDto;
import org.springframework.security.core.userdetails.User;

public interface AuthService {

    Message registerUser(RegisterRequestDto requestDto);
}
