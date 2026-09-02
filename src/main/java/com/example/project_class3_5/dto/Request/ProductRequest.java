package com.example.project_class3_5.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name must not be blank")
    private String name;

    private String description;

    @NotNull(message = "Price must not be null")
    private BigDecimal price;

    @NotNull(message = "Stock must not be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @NotNull(message = "Category ID must not be null")
    private Integer categoryId;

    private MultipartFile file;
}
