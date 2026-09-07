package com.example.project_class3_5.service.Impl;

import com.example.project_class3_5.dto.Request.CategoryRequest;
import com.example.project_class3_5.dto.Response.CategoryResponse;
import com.example.project_class3_5.entities.Category;
import com.example.project_class3_5.exception.ResourceNotFoundException;
import com.example.project_class3_5.mapper.CategoryMapper;
import com.example.project_class3_5.repo.CategoryRepository;
import com.example.project_class3_5.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        // map data of CategoryRequest ot CategoryEntity
        Category category = CategoryMapper.toEntity(request);

        // save entity to db
        Category saved = categoryRepository.save(category);

        return CategoryMapper.toCategoryResponse(saved);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id: " + id)
        );

        return CategoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
