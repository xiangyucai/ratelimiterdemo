package com.caixy.demo.ratelimiter.exception;

import java.util.Map;

public class UserNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NOT_FOUND, data);
    }
}
