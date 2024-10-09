package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // 스프링 설정 클래스 표시
@EnableWebSecurity // 보안 설정
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	
        http.authorizeRequests()
              .requestMatchers("/register").permitAll()
              .requestMatchers("/assets/*", "/css/*", "/js/*").permitAll()
              .requestMatchers("/").authenticated()
              .requestMatchers("/board/*").hasAnyRole("ADMIN", "USER") //게시물 관리는 관리자 또는 사용자이면 접근 가능
              .requestMatchers("/comment/*").hasAnyRole("ADMIN", "USER")
              .requestMatchers("/member/*").hasRole("ADMIN"); //회원 관리는 관리자이면 접근 가능

        
        // 인증이 필요하면 로그인 페이지를 보여주도록 설정 (시큐리티가 제공하는 기본 로그인페이지)
//        http.formLogin(); 
        //csrf 기능을 비활성화. 이제 CSRF 토큰이 발급 안됨
        http.csrf(csrf -> csrf.disable());
        // 로그아웃 처리
//        http.logout(); 

        //커스텀 로그인 페이지와 처리 주소 설정 
        http.formLogin( form -> 
        					form.loginPage("/customlogin")
        					.loginProcessingUrl("/login")
                            .permitAll()
                            .successHandler((request, response, authentication) -> {
                                response.sendRedirect("/"); // 로그인 성공 시 리다이렉트
                            })
                      );
        
// 스프링 버전이 다를 경우
//        //커스텀 로그인 페이지 설정
//        http.formLogin()
//                .loginPage("/customlogin") // 로그인 화면 주소
//                .loginProcessingUrl("/login") // 로그인 처리 주소
//                .successHandler(
//                        new AuthenticationSuccessHandler() {
//							@Override
//							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//								response.sendRedirect("/");
//							}
//                        }
//                )
//                .permitAll(); // 접근 권한

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
