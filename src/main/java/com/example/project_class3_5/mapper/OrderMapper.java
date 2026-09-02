package com.example.project_class3_5.mapper;

import com.example.project_class3_5.dto.Response.OrderItemResponse;
import com.example.project_class3_5.dto.Response.OrderResponse;
import com.example.project_class3_5.entities.Order;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        if (order == null) return null;

        List<OrderItemResponse> itemResponses = order.getOrderItems() != null ?
                order.getOrderItems().stream()
                        .map(OrderItemMapper::toResponse)
                        .collect(Collectors.toList()) : Collections.emptyList();

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .username(order.getUser() != null ? order.getUser().getUsername() : null)
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .updatedAt(order.getUpdatedAt())
                .orderItems(itemResponses)
                .build();
    }
}
