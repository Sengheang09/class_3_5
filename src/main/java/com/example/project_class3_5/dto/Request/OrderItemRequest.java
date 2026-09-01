package com.example.project_class3_5.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemRequest {

    @NotNull(message = " Id is required.")
    private Long productId;

    @Min(value = 1 , message = "Product quantity is required")
    private Integer quantity;

}
