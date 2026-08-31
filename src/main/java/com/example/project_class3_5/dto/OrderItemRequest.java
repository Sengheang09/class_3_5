package com.example.project_class3_5.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequest {

    @NotNull
    private Long productId;

    @Min(value = 1)
    private Integer quantity;

}
