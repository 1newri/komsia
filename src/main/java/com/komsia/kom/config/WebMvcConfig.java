package com.komsia.kom.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Qualifier(value = "httpInterceptor")
	private HandlerInterceptor handlerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/admin/**");
	}
}
