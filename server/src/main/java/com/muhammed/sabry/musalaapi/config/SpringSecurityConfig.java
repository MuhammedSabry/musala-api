package com.muhammed.sabry.musalaapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/device", "/device/**").permitAll()
				.antMatchers("/gateway", "/gateway/**").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest()
				.authenticated();
		
	}
}