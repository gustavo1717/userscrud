package com.example.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		http.csrf().disable();
		
		http.httpBasic(withDefaults());

		return http.build();
		
	}
	
	
//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(auth -> {
//	                auth.requestMatchers("/users/**").permitAll(); // Allow access to /users without authentication
//	                auth.anyRequest().authenticated();             // Require authentication for all other requests
//	            })
//	            .csrf().disable() // Disable CSRF for simplicity; re-enable in production
//	            .httpBasic(withDefaults()); // Enable HTTP Basic authentication
//
//	        return http.build();
//	    }

}