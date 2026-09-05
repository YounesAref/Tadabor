package com.younesaref.habittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.younesaref.habittracker"
})
public class HabittrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabittrackerApplication.class, args);
	}

}
