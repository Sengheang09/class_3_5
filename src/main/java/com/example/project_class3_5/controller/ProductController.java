package com.example.project_class3_5.controller;

import com.example.project_class3_5.dto.Request.ProductRequest;
import com.example.project_class3_5.dto.Response.ApiResponse;
import com.example.project_class3_5.dto.Response.ProductResponse;
import com.example.project_class3_5.service.Impl.ProductServiceImpl;
import com.example.project_class3_5.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @ModelAttribute ProductRequest request
    ){
        ProductResponse productResponse = productService.createProduct(request);

        return new ResponseEntity<>(ApiResponse.success("Product Created", productResponse), HttpStatus.CREATED);
    }

}
