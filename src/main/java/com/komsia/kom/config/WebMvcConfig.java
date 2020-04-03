package com.komsia.kom.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@Cacheable
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Qualifier(value = "httpInterceptor")
	private HandlerInterceptor handlerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**"
						, "/img/**"
						, "/js/**"
						, "/lib/**"
						, "/menu/**"
						, "/error"
						, "/favicon.ico");
	}
}
