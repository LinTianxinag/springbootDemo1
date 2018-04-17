package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ImportResource("classpath:DemoApplication.xml")
public class DemoApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
