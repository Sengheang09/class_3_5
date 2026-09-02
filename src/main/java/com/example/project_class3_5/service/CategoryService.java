package com.example.project_class3_5.service;

import com.example.project_class3_5.dto.Request.CategoryRequest;
import com.example.project_class3_5.dto.Response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse getCategoryById(Integer id);
    List<CategoryResponse> getAllCategories();
    CategoryResponse updateCategory(Integer id, CategoryRequest request);
    void deleteCategory(Integer id);
}
