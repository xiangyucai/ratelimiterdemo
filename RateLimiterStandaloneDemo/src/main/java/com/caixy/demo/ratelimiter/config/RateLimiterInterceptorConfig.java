package com.caixy.demo.ratelimiter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.caixy.demo.ratelimiter.interceptor.RateLimiterInterceptor;

@Configuration
public class RateLimiterInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private RateLimiterInterceptor rateLimiterInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/order");
	}

	
}
