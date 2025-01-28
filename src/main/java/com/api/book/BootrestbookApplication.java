package com.api.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BootrestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootrestbookApplication.class, args);
	}


	@Bean
	CommandLineRunner startingPoint()
	{
		return (args)->{
			System.out.println("This is the statement from CommandLineRunner Function-Interface.");

			Arrays.stream(args).forEach(arg-> System.out.println(arg));
		};
	}

}
