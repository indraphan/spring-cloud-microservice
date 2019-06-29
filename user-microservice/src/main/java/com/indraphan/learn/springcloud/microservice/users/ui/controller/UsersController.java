package com.indraphan.learn.springcloud.microservice.users.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping(path = "/status/check")
	public String status() {
		return "working";
	}
}
