package com.poly.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.poly.app")
@EnableScheduling
public class AssgimentHtMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssgimentHtMobileApplication.class, args);
	}


	
}
