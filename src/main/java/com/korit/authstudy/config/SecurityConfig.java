package com.korit.authstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 요청을 보내는 쪽의 도메인(사이트 주소)
        corsConfiguration.addAllowedOriginPattern("*");
        // 요청을 보내는 쪽의 Request, Response HEADER 정보에 대한 제약
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf-> csrf.disable());
        http.formLogin(formLogin -> formLogin.disable());   // 서버사이드 렌더링 로그인 방식 비활성화
        http.httpBasic(httpBasic -> httpBasic.disable());   // HTTP 프로토콜 기본 로그인 방식 비활성화
        http.logout(logout -> logout.disable());

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}
