package com.example.project_class3_5.exception;

import com.cloudinary.Api;
import com.example.project_class3_5.dto.Response.ApiResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Builder
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex
    ){
        return new ResponseEntity<>(
                ApiResponse.error("Error: "+ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(
            BadRequestException ex
    ){
        return new ResponseEntity<>(
                ApiResponse.error("Error: "+ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String , String>>> handleValidationException(
            MethodArgumentNotValidException ex
    ){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldError = ((FieldError) error).getField();
                    String errorMsg = error.getDefaultMessage();

                    errors.put(fieldError, errorMsg);
                }
        );

        return new ResponseEntity<>(
                ApiResponse.<Map<String , String>>builder()
                        .success(false)
                        .message("Validation")
                        .data(errors)
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
