package com.likelion.hospital.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Request IP:{}, {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
        // 프록시나 로드 밸런서 사용시 req.getHeader("X-Forwarded-For"); 활용
        filterChain.doFilter(request, response);
    }
}
