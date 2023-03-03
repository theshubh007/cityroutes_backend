package com.shubh.cityroutes_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.shubh.cityroutes_backend.CustomerFiles.Customer_controller;


// @SpringBootApplication
@SpringBootApplication

@ComponentScan(basePackages="com.shubh.cityroutes_backend.CustomerFiles")


public class CityroutesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityroutesBackendApplication.class, args);
	}

}
