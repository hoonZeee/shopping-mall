package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유정비니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다."),
    BASE_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "베이스 제품 옵션이 존재하지 않습니다."),
    INVALID_STATUS_TRANSITION(HttpStatus.UNPROCESSABLE_ENTITY, "해당 상태로 전환할 수 없습니다. from={0}, to={1}");

    public final HttpStatus httpStatus;

    public final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
