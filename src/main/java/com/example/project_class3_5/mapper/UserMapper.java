package com.example.project_class3_5.mapper;

import com.example.project_class3_5.dto.Request.UserRequest;
import com.example.project_class3_5.dto.Response.UserResponse;
import com.example.project_class3_5.entities.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toEntity(UserRequest request) {
        if (request == null) return null;
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }
}
