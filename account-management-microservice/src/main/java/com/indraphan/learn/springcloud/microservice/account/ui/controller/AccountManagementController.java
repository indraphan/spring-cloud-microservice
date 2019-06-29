package com.indraphan.learn.springcloud.microservice.account.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountManagementController {

	@GetMapping(path = "/status/check")
	public String status() {
		return "Account Management working";
	}
}
