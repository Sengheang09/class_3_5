package com.example.project_class3_5.service;

import com.example.project_class3_5.dto.Request.OrderRequest;
import com.example.project_class3_5.dto.Response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
    List<OrderResponse> getAllOrders();
    OrderResponse updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
}
