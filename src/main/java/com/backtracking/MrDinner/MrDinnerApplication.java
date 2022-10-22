package com.backtracking.MrDinner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MrDinnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrDinnerApplication.class, args);
	}

}
