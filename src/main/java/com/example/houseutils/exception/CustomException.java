package com.example.houseutils.exception;

/**
 * Enum을 활용한 커스텀 예외 작성 예제
 */
public class CustomException extends RuntimeException{

    private ErrorCode errorCode;
    private String string;

    public CustomException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String string) {
        super(string);
        this.errorCode = errorCode;
        this.string = string;
    }
}
