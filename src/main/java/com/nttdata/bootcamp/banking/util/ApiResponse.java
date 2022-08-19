package com.nttdata.bootcamp.banking.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private boolean error;
    private int code;
    private String title;
    private String message;
    private Object data;

    public ApiResponse() {

    }

    public static ApiResponse success(String title, String message, Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(false);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setTitle(title);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static ApiResponse success(String message, Object data) {
        return success("", message, data);
    }

    public static ApiResponse failed(String title, String message, int code) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(true);
        apiResponse.setCode(code);
        apiResponse.setTitle(title);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public static ApiResponse failed(String message, int code) {
        return ApiResponse.failed("", message, code);
    }

    public static ApiResponse failed(String message) {
        return ApiResponse.failed(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static ApiResponse failed(String title, String message) {
        return ApiResponse.failed(title, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
