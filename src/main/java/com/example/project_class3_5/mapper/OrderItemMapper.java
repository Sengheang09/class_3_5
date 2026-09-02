package com.example.project_class3_5.mapper;

import com.example.project_class3_5.dto.Response.OrderItemResponse;
import com.example.project_class3_5.entities.OrderItem;

import java.math.BigDecimal;

public class OrderItemMapper {

    public static OrderItemResponse toResponse(OrderItem item) {
        if (item == null) return null;
        BigDecimal itemPrice = item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO;
        BigDecimal subtotal = itemPrice.multiply(BigDecimal.valueOf(item.getQuantity()));

        return OrderItemResponse.builder()
                .id(item.getId())
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .productName(item.getProduct() != null ? item.getProduct().getName() : null)
                .quantity(item.getQuantity())
                .price(itemPrice)
                .subtotal(subtotal)
                .build();
    }
}
