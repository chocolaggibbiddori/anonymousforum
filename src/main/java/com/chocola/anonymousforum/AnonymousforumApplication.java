package com.chocola.anonymousforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AnonymousforumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnonymousforumApplication.class, args);
	}

}
