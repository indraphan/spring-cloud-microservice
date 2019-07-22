package com.indraphan.learn.springcloud.microservice.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.indraphan.learn.springcloud.microservice.users.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
}
