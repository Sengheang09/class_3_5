package com.example.cloud_35.service;

import com.example.cloud_35.dto.Request.ImageRequestDto;
import com.example.cloud_35.dto.Response.ImageResponseDto;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ImageService {

    ImageResponseDto createImage(ImageRequestDto requestDto) throws IOException;

    List<ImageResponseDto> getAllImages();

    ImageResponseDto getImageById(Long id);

    ImageResponseDto updateImage(Long id , ImageRequestDto requestDto) throws IOException;

    void deleteImage(Long id);

}
