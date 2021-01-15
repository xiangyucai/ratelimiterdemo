package com.caixy.demo.ratelimiter.interceptor;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.caixy.demo.ratelimiter.interceptor.util.StandaloneRateLimiter;
import com.caixy.demo.ratelimiter.models.User;
import com.caixy.demo.ratelimiter.services.UserService;

@SuppressWarnings("deprecation")
@Component
public class RateLimiterInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(RateLimiterInterceptor.class);

    @Autowired
    private UserService userService;

    private static Map<String, StandaloneRateLimiter> limiterCache = new ConcurrentHashMap<String, StandaloneRateLimiter>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("Entered preHandle interceptor.");
        String userid = request.getParameter("userid");
        logger.info("user id: " + userid);
        StandaloneRateLimiter limiter;
        try {
            limiter = getRateLimiter(userid);
        } catch (IllegalArgumentException ie) {
            String contextStr = userid == null ? "/order/invalidUserId?userid=NULL" : "/order/invalidUserId";
            request.getRequestDispatcher(contextStr).forward(request, response);
            return false;
        } catch (NoSuchElementException ne) {
            request.getRequestDispatcher("/order/userNotFound").forward(request, response);
            return false;
        }
        boolean allowRequest = limiter.tryAcquire();

        return allowRequest;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.info("Entered postHandle interceptor.");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("Entered afterCompletion iterceptor.");
    }

    private StandaloneRateLimiter getRateLimiter(String userid) {
        if (userid == null || !isInteger(userid)) {
            throw new IllegalArgumentException();
        }
        if (limiterCache.containsKey(userid)) {
            return limiterCache.get(userid);
        } else {
            synchronized (userid.intern()) {
                if (limiterCache.containsKey(userid)) {
                    return limiterCache.get(userid);
                }
                User user = userService.getUserById(Integer.parseInt(userid));
                logger.info(user.toString());
                StandaloneRateLimiter limiter = StandaloneRateLimiter.create(user.getTps(), TimeUnit.MINUTES);
                limiterCache.put(userid, limiter);
                return limiter;
            }
        }

    }

    private boolean isInteger(String intstr) {
        try {
            Integer.parseInt(intstr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @PreDestroy
    public void destroy() {
        logger.info("PreDestroy");
        for (StandaloneRateLimiter limiter : limiterCache.values()) {
            try {
                limiter.stop();
            } catch (Exception e) {
                logger.info(e.getLocalizedMessage());
            }
        }
        limiterCache.clear();
    }

}
