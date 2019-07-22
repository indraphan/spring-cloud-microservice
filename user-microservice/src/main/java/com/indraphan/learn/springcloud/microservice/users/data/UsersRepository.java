package com.indraphan.learn.springcloud.microservice.users.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

	//query method findBy...
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

}
