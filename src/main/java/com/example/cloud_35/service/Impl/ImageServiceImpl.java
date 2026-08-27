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
        return repository.findAll().stream()
                .map(img -> mapper.toImageResponseDto(img))
                .toList();
    }

    @Override
    public ImageResponseDto getImageById(Long id) {
        return null;
    }

    @Override
    public ImageResponseDto updateImage(Long id, ImageRequestDto requestDto) throws IOException {

        ImageEntity oldImage = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));


        if(requestDto == null){
            return null;
        }

        if(requestDto.getFile() == null || requestDto.getFile().isEmpty()){
            return null;
        }

        Map newImage = cloudService.uploadImage(requestDto.getFile());

        String newUrl = newImage.get("url").toString();
        String newPublicId = newImage.get("public_id").toString();

        // delete image on cloudinary
        cloudService.deleteImage(oldImage.getPublicId());

        // set new data
        oldImage.setName(requestDto.getName());
        oldImage.setImageUrl(newUrl);
        oldImage.setPublicId(newPublicId);

        // save to db again
        ImageEntity updated = repository.save(oldImage);

        // map to response dto
        return mapper.toImageResponseDto(updated);
    }

    @Override
    public void deleteImage(Long id) {

    }
}
