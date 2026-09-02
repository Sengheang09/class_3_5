package com.example.project_class3_5.mapper;

import com.example.project_class3_5.dto.Request.CategoryRequest;
import com.example.project_class3_5.dto.Response.CategoryResponse;
import com.example.project_class3_5.entities.Category;

public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        if (category == null) return null;
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public static Category toEntity(CategoryRequest request) {
        if (request == null) return null;
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
