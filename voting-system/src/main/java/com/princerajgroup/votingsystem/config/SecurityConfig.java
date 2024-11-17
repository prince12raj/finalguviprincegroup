package com.princerajgroup.votingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration // Added this annotation
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    @SuppressWarnings({ "removal", "deprecation" })
	protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF protection for simplicity (not recommended for production)
            .authorizeRequests()
            .requestMatchers("/api/auth/**").permitAll() // Allow public access to auth endpoints
            .requestMatchers("/api/vote/**").authenticated() // Protect vote endpoints
            .and()
            .httpBasic(); // Enable basic authentication
    }
}
