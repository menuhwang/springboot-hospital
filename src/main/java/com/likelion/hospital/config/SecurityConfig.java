package com.likelion.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
                .antMatchers(HttpMethod.GET, "/*", "/hospitals*", "/boards*", "/boards/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/hospitals", "/api/v1/boards", "/api/v1/boards/*", "/api/v1/replies").permitAll()
                .antMatchers("/api/v1/users/signup", "/api/v1/users/signin").permitAll()
                .antMatchers("/boards/new", "/boards/edit/*").authenticated()
                .anyRequest().authenticated();

        return http.build();
    }
}
