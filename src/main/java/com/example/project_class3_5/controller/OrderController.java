package com.example.project_class3_5.controller;

import com.example.project_class3_5.dto.Request.OrderRequest;
import com.example.project_class3_5.dto.Response.ApiResponse;
import com.example.project_class3_5.dto.Response.OrderResponse;
import com.example.project_class3_5.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/order", "/api/orders"})
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest request
    ) {
        OrderResponse orderResponse = orderService.createOrder(request);

        return new ResponseEntity<>(
                ApiResponse.success("Order created successfully", orderResponse),
                HttpStatus.CREATED
        );
    }
}
