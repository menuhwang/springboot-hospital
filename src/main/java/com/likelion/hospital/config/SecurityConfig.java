package com.likelion.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors();

        http.authorizeHttpRequests()
                .antMatchers("/api/v1/hospitals/**").permitAll()
                .antMatchers("/api/v1/users/signup", "/api/v1/users/signin").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
