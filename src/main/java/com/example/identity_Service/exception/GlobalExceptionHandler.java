package com.example.identity_Service.exception;

import com.example.identity_Service.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    không xác định
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);

    }

//set error
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> runtimeExceptionHandler(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);

    }

        @ExceptionHandler(value = AccessDeniedException.class)
        ResponseEntity<ApiResponse> hanlingAccessDeniedExeption(AccessDeniedException AccessDeniedException){
            ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
            return ResponseEntity.status(errorCode.getStatusCode()).body(
                    ApiResponse.builder()
                            .code(errorCode.getCode())
                            .message(errorCode.getMessage())
                            .build()
            );

        }

//    validate
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

            ErrorCode errorCode = ErrorCode.ERROR_CODE;
        try {
             errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }




}

