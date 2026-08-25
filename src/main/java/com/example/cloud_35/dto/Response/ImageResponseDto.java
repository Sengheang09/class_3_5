package com.example.cloud_35.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDto {
    private Long id;

    private String name;

    private String imageUrl;

    private String createdAt;

    private String updatedAt;
}
