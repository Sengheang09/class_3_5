package com.example.project_class3_5.controller;

import com.example.project_class3_5.dto.Request.CategoryRequest;
import com.example.project_class3_5.dto.Response.ApiResponse;
import com.example.project_class3_5.dto.Response.CategoryResponse;
import com.example.project_class3_5.service.Impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request
    ){

        CategoryResponse categoryResponse = categoryService.createCategory(request);

        return new ResponseEntity<>(
                ApiResponse.success("Category created successfully",categoryResponse),
                HttpStatus.CREATED
        );
    }



}
