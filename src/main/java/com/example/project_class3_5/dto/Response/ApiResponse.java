package com.example.project_class3_5.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(String msg , T data){
        return ApiResponse.<T>builder()
                .success(true)
                .message(msg)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
//        ApiResponse<T> obj = new ApiResponse<>();
//        obj.setSuccess(true);
//        obj.setMessage(msg);
//        obj.setData(data);
//        obj.setTimestamp(LocalDateTime.now());
//        return obj;
    }
    public static <T> ApiResponse<T> error(String message){
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
