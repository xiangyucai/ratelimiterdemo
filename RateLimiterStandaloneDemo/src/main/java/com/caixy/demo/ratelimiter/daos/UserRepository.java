package com.caixy.demo.ratelimiter.daos;

import org.springframework.data.repository.CrudRepository;

import com.caixy.demo.ratelimiter.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
