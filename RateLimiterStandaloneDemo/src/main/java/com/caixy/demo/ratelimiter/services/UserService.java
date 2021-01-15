package com.caixy.demo.ratelimiter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caixy.demo.ratelimiter.daos.UserRepository;
import com.caixy.demo.ratelimiter.models.User;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Look up the user by user id
     * @param id user id
     * @return user
     * @throws java.util.NoSuchElementException when the user can not be found by user id
     */
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

}
