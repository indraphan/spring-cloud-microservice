package com.indraphan.learn.springcloud.microservice.albums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlbumMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumMicroserviceApplication.class, args);
	}

}
