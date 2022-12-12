package com.likelion.hospital.config;

import com.likelion.hospital.utils.JwtAuthenticationFilter;
import com.likelion.hospital.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenUtil jwtTokenUtil;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors();

        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/", "/hospitals*", "/boards*", "/boards/*", "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/hospitals", "/api/v1/boards").permitAll()
                .antMatchers("/api/v1/users/signup", "/api/v1/users/signin", "/api/v1/users/signout").permitAll()
                .antMatchers("/boards/new", "/boards/edit/*").authenticated()
                .anyRequest().authenticated();

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
