package com.example.demo;

import com.example.demo.config.AwsConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AwsConfigProperties.class)
public class LocalstackSnsSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalstackSnsSqsApplication.class, args);
	}

}
