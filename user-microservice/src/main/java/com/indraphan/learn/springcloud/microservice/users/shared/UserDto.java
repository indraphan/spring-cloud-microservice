package com.indraphan.learn.springcloud.microservice.users.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5814963998202360530L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
}
