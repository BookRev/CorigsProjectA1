package com.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Run{

	public static void main(String[] args) {
	 	SpringApplication.run(Run.class, args);
	}

}	
//#spring.datasource.schema=file:src/main/resources/schema.sql
//#spring.datasource.data=file:src/main/resources/data.sql
