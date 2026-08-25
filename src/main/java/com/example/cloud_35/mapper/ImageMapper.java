package com.example.cloud_35.mapper;

import com.example.cloud_35.dto.Response.ImageResponseDto;
import com.example.cloud_35.entity.ImageEntity;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ImageMapper {

    public ImageResponseDto toImageResponseDto(ImageEntity imageEntity) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();

        // set id
        Long id = imageEntity.getId();
        imageResponseDto.setId(id);

        // set name
        imageResponseDto.setName(imageEntity.getName());

        // set image url
        imageResponseDto.setImageUrl(imageEntity.getImageUrl());

        imageResponseDto.setCreatedAt(imageEntity.getCreatedAt().toString());
        imageResponseDto.setUpdatedAt(imageEntity.getUpdatedAt().toString());

        return imageResponseDto;
    }
}
