package com.example.cloud_35.controller;

import com.example.cloud_35.dto.Request.ImageRequestDto;
import com.example.cloud_35.dto.Response.ApiResponse;
import com.example.cloud_35.dto.Response.ImageResponseDto;
import com.example.cloud_35.service.ImageService;
import com.example.cloud_35.service.Impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageController {

    private ImageService imageService;
    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ImageResponseDto>> create(
            @ModelAttribute ImageRequestDto requestDto
    ){

        try {
             ImageResponseDto responseDto = imageService.createImage(requestDto);

             return ResponseEntity.status(HttpStatus.CREATED)
                     .body(
                             new ApiResponse<>(
                                     true,
                                     "Data have been created",
                                     responseDto
                             )
                     );
        }catch(Exception e){
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            false,
                            "Error: "+e.getMessage(),
                            null
                    )
            );
        }

    }

}
