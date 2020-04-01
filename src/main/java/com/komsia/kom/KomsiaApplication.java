package com.komsia.kom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ConfigurationProperties
public class KomsiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomsiaApplication.class, args);
	}

}
