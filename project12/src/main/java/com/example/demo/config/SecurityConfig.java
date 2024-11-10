package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.UserDetailsServiceImpl;

// 스프링 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	UserDetailsService customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
      http.formLogin();
      http.csrf().disable();
      http.logout();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
      // CSRF 비활성화
      http.csrf().disable(); 
      // 세션 사용안함
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
      
      // 인증 없이 접근 가능한 경로 설정 (로그인 등)
      http.authorizeHttpRequests()
          .requestMatchers("/login", "/register").permitAll() 
          .anyRequest().authenticated(); // 나머지 요청은 인증 필요

      // formLogin 비활성화
      http.formLogin().disable();

      return http.build();
	}

}
