package com.example.demo.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.ApiCheckFilter;
import com.example.demo.security.ApiLoginFilter;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;

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

	// 필터를 빈으로 등록하면, 자동으로 필터 체인에 추가됨
	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter(customUserDetailsService());
	}

	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}
	
	// 웹사이트: 세션 기반 인증 + 폼로그인
	// => 사용자가 폼에서 아이디와 패스워드를 입력 > 세션아이디 부여 > 쿠키에 저장
	// => 브라우저가 자동으로 세션아이디를 전송하므로 사용자는 인증정보를 전송할 필요 없음
	// API: 토큰 기반 인증
	// => 로그인 API로 아이디와 패스워드를 입력 > 토큰 발급 > 로컬 저장소에 저장
	// => 서버가 세션을 관리하지 않으므로 부담이 없음
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// CSRF 비활성화
		http.csrf().disable();
		// 세션 사용안함
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 인증 없이 접근 가능한 경로 설정 (로그인 등)
		// 처음에는 모든 경로를 권한이 없는 사용자에게 허용
		http.authorizeHttpRequests()
		.requestMatchers("/login", "/register","/board/*","/member/*").permitAll();

		// formLogin 비활성화
		http.formLogin().disable();
		
		// UsernamePasswordFilter: 폼로그인에서 사용되는 필터
		// apiCheckFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정
		http.addFilterBefore(apiCheckFilter(), 
							UsernamePasswordAuthenticationFilter.class);
		
		/* API 로그인 필터에 필요한 인증 매니저 생성 */
		// 인증매니저 설정
 		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);	
 		authenticationManagerBuilder.userDetailsService(customUserDetailsService())
					 				.passwordEncoder(passwordEncoder());	
 		
 		// 인증매니저 생성
 		AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

 		// 시큐리티에 인증매니저 등록
 		http.authenticationManager(authenticationManager);
 		
 		// ApiLoginFilter 생성 및 등록
 		// /login 요청이 들어오면 필터가 실행됨
		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", memberService());	
		// 로그인에 실패했을 때 핸들러가 실행됨
		apiLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler());	
		apiLoginFilter.setAuthenticationManager(authenticationManager);

		// UsernamePasswordFilter는 폼로그인에서 사용하는 필터이다
		// apiLoginFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정한다
		 http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	// 로그인 실패 핸들러 구현 및 등록
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		
		// AuthenticationFailureHandler 익명 클래스로 만들기
		AuthenticationFailureHandler handler = new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				System.out.println("login fail handler........");
				
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json;charset=utf-8");
				JSONObject json = new JSONObject();
				json.put("code", "401");
				json.put("message", exception.getMessage());

				PrintWriter out = response.getWriter();
				out.print(json);
			}
		};
		return handler;
	}

}
