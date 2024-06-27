package com.example.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration // 스프링 설정 클래스 표시
@EnableWebSecurity // 보안 설정
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
              .requestMatchers("/register").permitAll()
              .requestMatchers("/assets/*", "/css/*", "/js/*").permitAll()
              .requestMatchers("/").authenticated()
              .requestMatchers("/board/*").hasAnyRole("ADMIN", "USER") //게시물 관리는 관리자 또는 사용자이면 접근 가능
                .requestMatchers("/comment/*").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/member/*").hasRole("ADMIN"); //회원 관리는 관리자이면 접근 가능


        http.formLogin(); //시큐리티가 제공하는 기본 로그인페이지 사용하기
        http.csrf().disable(); //csrf는 get을 제외하여 상태값을 위조(변경)할 수있는 post,put,delete 메소드를 막음
        http.logout(); // 로그아웃 처리

        //커스텀 로그인 페이지 설정
        http.formLogin()
                .loginPage("/customlogin") // 로그인 화면 주소
                .loginProcessingUrl("/login") // 로그인 처리 주소
                .successHandler(
                        new AuthenticationSuccessHandler() {
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
								response.sendRedirect("/");
							}
                        }
                )
                .permitAll(); // 접근 권한

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
