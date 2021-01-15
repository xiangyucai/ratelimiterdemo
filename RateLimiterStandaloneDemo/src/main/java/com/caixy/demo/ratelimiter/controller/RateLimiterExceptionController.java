package com.caixy.demo.ratelimiter.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caixy.demo.ratelimiter.exception.InvalidDataException;
import com.caixy.demo.ratelimiter.exception.TooManyRequestsException;
import com.caixy.demo.ratelimiter.exception.UserNotFoundException;

@RestController
public class RateLimiterExceptionController {

    Logger logger = LoggerFactory.getLogger(RateLimiterExceptionController.class);

    @GetMapping("/order/userNotFound")
    public void throwUserNotFoundException(@RequestParam(name = "userid") String userid) {
        logger.info("User with id " + userid + " can not be found");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("User id: ", userid);
        throw new UserNotFoundException(Collections.unmodifiableMap(data));
    }

    @GetMapping("/order/invalidUserId")
    public void throwInvalidDataException(@RequestParam(name = "userid") String userid) {
        logger.info("User id " + userid + " is invalid");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("User id: ", userid);
        throw new InvalidDataException(Collections.unmodifiableMap(data));
    }

    @GetMapping("/order/tooManyRequests")
    public void throwTooManyRequestsException(@RequestParam(name = "userid") String userid) {
        logger.info("User with id " + userid + " has too many requests");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("User id: ", userid);
        throw new TooManyRequestsException(Collections.unmodifiableMap(data));
    }
}
