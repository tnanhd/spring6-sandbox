package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestLocalstackSnsSqsApplication {

	public static void main(String[] args) {
		SpringApplication.from(LocalstackSnsSqsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
