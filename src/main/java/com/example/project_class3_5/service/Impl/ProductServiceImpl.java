package com.example.project_class3_5.service.Impl;

import com.example.project_class3_5.config.CloudinaryService;
import com.example.project_class3_5.dto.Request.ProductRequest;
import com.example.project_class3_5.dto.Response.ProductResponse;
import com.example.project_class3_5.entities.Category;
import com.example.project_class3_5.entities.Product;
import com.example.project_class3_5.exception.ResourceNotFoundException;
import com.example.project_class3_5.mapper.ProductMapper;
import com.example.project_class3_5.repo.CategoryRepository;
import com.example.project_class3_5.repo.ProductRepository;
import com.example.project_class3_5.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private CloudinaryService cloudinaryService;

    public ProductServiceImpl(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            CloudinaryService cloudinaryService
    ) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // request ->(mapper) Entity -> db ->(mapper) map to response
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+request.getCategoryId()));

        Map image = cloudinaryService.uploadImage(request.getFile());

        String imageUrl = (String) image.get("url");
        String publicId = (String) image.get("public_id");

        Product product = ProductMapper.toEntity(request);

        product.setCategory(category);
        product.setImageUrl(imageUrl);
        product.setPublicId(publicId);

        Product saved = productRepository.save(product);

        return ProductMapper.toResponse(saved);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: "+categoryId));

        List<Product> products = category.getProducts();

        // map list of product to response
        List<ProductResponse> responses = products.stream()
                .map(obj -> ProductMapper.toResponse(obj))
                .toList();

        return responses;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
