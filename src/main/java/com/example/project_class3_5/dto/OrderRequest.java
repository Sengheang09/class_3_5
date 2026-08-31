package com.example.project_class3_5.dto;

import com.example.project_class3_5.entities.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderRequest {

    private Long userId;

    private List<OrderItem> orderItems;

}
