package com.example.demo.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode code;

    public BusinessException(ErrorCode code, Object... args) {
        super(java.text.MessageFormat.format(code.message, args));
        this.code = code;
    }
}
