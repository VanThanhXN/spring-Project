package com.example.identity_Service.exception;

public enum ErrorCode {
    USER_EXISTED(1003," user existed"),
    UNCATEGORIZED_EXCEPTION(9999," UNCATEGORIZED  EXCEPTION"),
    USER_NOT_FOUND(1000,"tên ít nhất 3 kí tự"),
    PASS_NOT_FOUND(1002,"mật khau it nhat 6 ki tu"),
    ERROR_CODE(1009," validate error"),
    USER_NOT_EXISTED(1004," user not_existed"),
    UN_AUTHENTICATE(1006," UnAuthenticate"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
