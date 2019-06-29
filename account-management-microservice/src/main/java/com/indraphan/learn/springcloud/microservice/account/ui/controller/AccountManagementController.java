package com.indraphan.learn.springcloud.microservice.account.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountManagementController {
	
	@Autowired
	private Environment env;

	@GetMapping(path = "/status/check")
	public String status() {
		return "Account Management working on port " + env.getProperty("local.server.port");
	}
}
