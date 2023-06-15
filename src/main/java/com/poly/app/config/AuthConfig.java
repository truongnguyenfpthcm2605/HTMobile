package com.poly.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
	@Autowired
	AuthInterceptor authInterceptor;
	
	@Autowired
	MyInterceptor myInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/contact/**","/information/**","/admin/**","/bill/**")
		.excludePathPatterns("/assets/**", "/admin/home/index");
		
		registry.addInterceptor(myInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/assets/**");
	}

}
