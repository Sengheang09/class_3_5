package com.example.cloud_35.service.Impl;

import com.example.cloud_35.config.CloudinaryService;
import com.example.cloud_35.dto.Request.ImageRequestDto;
import com.example.cloud_35.dto.Response.ImageResponseDto;
import com.example.cloud_35.entity.ImageEntity;
import com.example.cloud_35.mapper.ImageMapper;
import com.example.cloud_35.repo.ImageRepository;
import com.example.cloud_35.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository repository;
    private ImageMapper mapper;
    private CloudinaryService cloudService;

    public ImageServiceImpl(
            ImageRepository repository,
            ImageMapper mapper,
            CloudinaryService cloudService
    ){
        this.repository = repository;
        this.mapper = mapper;
        this.cloudService = cloudService;
    }

    @Override
    public ImageResponseDto createImage(ImageRequestDto requestDto) throws IOException {

        if(requestDto == null){
            return null;
        }

        Map images = cloudService.uploadImage(requestDto.getFile());
        String url = images.get("url").toString();
        String publicId = images.get("public_id").toString();

        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setName(requestDto.getName());
        imageEntity.setImageUrl(url);
        imageEntity.setPublicId(publicId);

        ImageEntity saved = repository.save(imageEntity);

        ImageResponseDto responseDto = mapper.toImageResponseDto(saved);

        return responseDto;
    }

    @Override
    public List<ImageResponseDto> getAllImages() {
        return List.of();
    }

    @Override
    public ImageResponseDto getImageById(Long id) {
        return null;
    }

    @Override
    public ImageResponseDto updateImage(Long id, ImageRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteImage(Long id) {

    }
}
