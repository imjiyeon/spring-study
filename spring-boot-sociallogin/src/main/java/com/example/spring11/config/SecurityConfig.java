package com.example.spring11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 스프링 시큐리티 설정 클래스
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http.authorizeHttpRequests()
				.requestMatchers("/assets/*", "/css/*", "/js/*").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/").authenticated()
				.requestMatchers("/board/*").hasAnyRole("ADMIN","USER")
				.requestMatchers("/comment/*").hasAnyRole("ADMIN","USER")
				.requestMatchers("/member/*").hasAnyRole("ADMIN","USER"); // 소셜로그인 회원 정보를 수정하기 위해 변경

		http.formLogin()
				.defaultSuccessUrl("/",true);
		http.csrf().disable();
		http.logout();
		
		// aouth 로그인이 가능하도록 설정
		http.oauth2Login()
				.successHandler(new CustomAuthenticationSuccessHandler()); // 핸들러 등록

		return http.build();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
