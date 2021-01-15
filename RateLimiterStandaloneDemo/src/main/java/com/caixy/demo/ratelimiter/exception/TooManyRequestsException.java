package com.caixy.demo.ratelimiter.exception;

import java.util.Map;

public class TooManyRequestsException extends BaseException {
    private static final long serialVersionUID = 1L;

    public TooManyRequestsException(Map<String, Object> data) {
        super(ErrorCode.TOO_MANY_REQUESTS, data);
    }
}
