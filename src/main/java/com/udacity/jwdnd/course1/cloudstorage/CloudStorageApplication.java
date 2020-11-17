package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.validator.PasswordValidator;
import com.udacity.jwdnd.course1.cloudstorage.validator.UsernameValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

	@Bean
	public PasswordValidator passwordValidator() {
		return new PasswordValidator();
	}

	@Bean
	public UsernameValidator usernameValidator() {
		return new UsernameValidator();
	}

}
