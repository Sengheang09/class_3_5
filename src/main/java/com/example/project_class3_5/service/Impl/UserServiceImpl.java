package com.example.project_class3_5.service.Impl;

import com.example.project_class3_5.dto.Request.UserRequest;
import com.example.project_class3_5.dto.Response.UserResponse;
import com.example.project_class3_5.entities.User;
import com.example.project_class3_5.exception.BadRequestException;
import com.example.project_class3_5.mapper.UserMapper;
import com.example.project_class3_5.repo.UserRepository;
import com.example.project_class3_5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {

        boolean check = userRepository.existsByEmail(request.getEmail());

        if(check){
            throw new BadRequestException("Email already exists: "+request.getEmail());
        }

        User user = UserMapper.toEntity(request);

        User saved = userRepository.save(user);

        return UserMapper.toResponse(saved);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
