package com.weflux_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.weflux_playground.${sec}")
@EnableR2dbcRepositories(basePackages = "com.weflux_playground.${sec}")
public class WefluxPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(WefluxPlaygroundApplication.class, args);
	}

}
