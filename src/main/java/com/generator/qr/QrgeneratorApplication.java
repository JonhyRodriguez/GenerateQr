package com.generator.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QrgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrgeneratorApplication.class, args);
	}

}
