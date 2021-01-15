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

@RestController
public class RateLimiterDemoController {

    Logger logger = LoggerFactory.getLogger(RateLimiterDemoController.class);

    @GetMapping("/order")
    public String getOrder(@RequestParam(value = "orderid", defaultValue = "NULL") String orderid) {
        if ("NULL".equals(orderid)) {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Order id: ", "NULL");
            throw new InvalidDataException(Collections.unmodifiableMap(data));
        }
        logger.info("Return order");
        return "Order";
    }
}
