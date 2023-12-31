package com.example.springpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostApplication.class, args);
	}

}
