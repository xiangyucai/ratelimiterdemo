package com.caixy.demo.ratelimiter.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caixy.demo.ratelimiter.controller.RateLimiterDemoController;
import com.caixy.demo.ratelimiter.controller.RateLimiterExceptionController;
import com.caixy.demo.ratelimiter.exception.BaseException;
import com.caixy.demo.ratelimiter.exception.ErrorReponse;

@ControllerAdvice(assignableTypes = { RateLimiterDemoController.class, RateLimiterExceptionController.class })
@ResponseBody
public class RateLimiterExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest request) {
        ErrorReponse representation = new ErrorReponse(ex, request.getRequestURI());
        return new ResponseEntity<>(representation, new HttpHeaders(), ex.getError().getStatus());
    }

}
