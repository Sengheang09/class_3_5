package com.example.cloud_35.controller;

import com.example.cloud_35.dto.Request.ImageRequestDto;
import com.example.cloud_35.dto.Response.ApiResponse;
import com.example.cloud_35.dto.Response.ImageResponseDto;
import com.example.cloud_35.entity.ImageEntity;
import com.example.cloud_35.service.ImageService;
import com.example.cloud_35.service.Impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<ImageResponseDto>>> getImage(){

        List<ImageResponseDto> responseDto=imageService.getAllImages();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Data found",
                        responseDto
                )
        );
    }

    @PutMapping(value = "/update/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ImageResponseDto>> update(
            @PathVariable Long id,
            @ModelAttribute ImageRequestDto requestDto
    ){
        try{
            ImageResponseDto responseDto = imageService.updateImage(id, requestDto);

            if (responseDto == null) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse<>(
                                false,
                                "Image is required",
                                null
                        )
                );
            }

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Data have been updated",
                            responseDto
                    )
            );
        }catch(Exception e){
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            false,
                            "error: "+e.getMessage(),
                            null
                    )
            );
        }
    }

}
