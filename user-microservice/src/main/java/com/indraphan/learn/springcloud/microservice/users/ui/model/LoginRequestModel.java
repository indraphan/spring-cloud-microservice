package com.indraphan.learn.springcloud.microservice.users.ui.model;

import lombok.Data;

@Data
public class LoginRequestModel {
	private String email;
	private String password;
}
