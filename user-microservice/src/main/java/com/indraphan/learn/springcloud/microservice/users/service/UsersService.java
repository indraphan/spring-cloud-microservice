package com.indraphan.learn.springcloud.microservice.users.service;

import com.indraphan.learn.springcloud.microservice.users.shared.UserDto;

public interface UsersService {
	UserDto createUser(UserDto userDetails);
}
