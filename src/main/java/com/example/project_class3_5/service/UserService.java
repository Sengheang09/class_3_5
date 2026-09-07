package com.example.project_class3_5.service;

import com.example.project_class3_5.dto.Request.UserRequest;
import com.example.project_class3_5.dto.Response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}
