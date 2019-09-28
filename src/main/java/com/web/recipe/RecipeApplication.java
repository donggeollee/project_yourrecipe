package com.web.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.web.recipe")
@SpringBootApplication
public class RecipeApplication{

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
		
		System.out.println("서버 구동 완료");
	}
	
 
}
