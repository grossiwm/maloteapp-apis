package com.gabrielrossilopes.appmalote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMaloteApplication {

	private static Logger LOGGER = LoggerFactory
			.getLogger(AppMaloteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppMaloteApplication.class, args);
	}

}
