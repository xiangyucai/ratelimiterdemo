package com.caixy.demo.ratelimiter.exception;

import java.util.Map;

public class InvalidDataException extends BaseException {
    private static final long serialVersionUID = 1L;

    public InvalidDataException(Map<String, Object> data) {
        super(ErrorCode.REQUEST_VALIDATION_FAILED, data);
    }

}
