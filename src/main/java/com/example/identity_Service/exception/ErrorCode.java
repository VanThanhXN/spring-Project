package com.example.identity_Service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_EXISTED(1003," user existed",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999," UNCATEGORIZED  EXCEPTION",HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1000,"tên ít nhất 3 kí tự",HttpStatus.BAD_REQUEST),
    PASS_NOT_FOUND(1002,"mật khau it nhat 6 ki tu",HttpStatus.BAD_REQUEST),
    ERROR_CODE(1009," validate error",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004," user not_existed",HttpStatus.NOT_FOUND),
    UN_AUTHENTICATE(1006," UnAuthenticate", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZE(1007," You do not have premission", HttpStatus.FORBIDDEN),
    INVALIT_DOB(1097," do tuoi k hop le", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }



}
