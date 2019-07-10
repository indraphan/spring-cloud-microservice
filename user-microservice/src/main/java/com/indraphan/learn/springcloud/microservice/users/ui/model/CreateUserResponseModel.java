package com.indraphan.learn.springcloud.microservice.users.ui.model;

import lombok.Data;

@Data
public class CreateUserResponseModel {
	private String firstName;
	private String lastName;
	private String email;
	private String userId;
}
