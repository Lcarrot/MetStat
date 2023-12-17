package ru.itis.metstat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetstatApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetstatApplication.class, args);
	}

}
