package com.capstone.sheepsheadbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class SheepsheadBackendApplication {

	public static void main(String[] args) { SpringApplication.run(SheepsheadBackendApplication.class, args); }

	@Autowired
	private Environment env;

	@Bean
	public WebMvcConfigurer corsConfigurer() {

		String ip = env.getProperty("server.address");
		String url = "http://" + ip + ":4200";
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins(url)
				.allowedMethods("*")
				.allowedHeaders("*");
			}
		};
	}
}
