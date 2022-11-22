package com.backtracking.MrDinner;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class MrDinnerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MrDinnerApplication.class, args);
	}

	//@Bean
	//Hibernate5Module hibernate5Module(){
	//	return new Hibernate5Module();
	//}
}
