package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.ApiCheckFilter;
import com.example.demo.security.ApiLoginFilter;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;

// Spring Security 설정 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// API Check 필터에서 인증객체를 생성할 때 사용됨
	@Bean
	UserDetailsService customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	// 회원가입시 패스워드를 암호화할때, 로그인시 패스워드를 대조할 때 사용됨
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 데이터베이스에서 회원 정보를 조회하는 서비스
	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}
	
	// 필터를 빈으로 등록하면, 자동으로 필터 체인에 추가됨
	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter(customUserDetailsService());
	}

	// 웹사이트의 인증 방식:
	// 사용자는 로그인 폼에 아이디와 패스워드를 입력하여 인증한다
	// 인증이 완료되면 서버는 세션id를 생성하여 사용자 브라우저에 저장한다
	// 서버는 세션 id를 기반으로 사용자 상태를 관리한다
	
	// API의 인증 방식:
	// 사용자는 로그인 API를 호출하여 아이디와 패스워드를 입력한다.
	// 인증이 완료되면 서버는 토큰을 발급한다
	// 사용자는 토큰을 로컬 스토리지에 저장한다
	// 이후 API 호출시 요청 헤더에 토큰을 포함하여 인증을 진행한다
	// *토큰 기반 인증은 서버가 세션 상태를 관리하지 않기 때문에 서버의 부담이 줄어드는 장점이 있다

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// 폼 로그인 기능 비활성화
		http.formLogin().disable();
		
		// 세션 상태 관리 비활성화 (서버에서 세션 생성 안함)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// 인증 없이 접근 가능한 경로 설정
		// 나중에 커스텀 필터에서 권한 관리를 추가할 예정
		http.authorizeHttpRequests()
		.requestMatchers("/login", "/register","/board/*","/member/*").permitAll();

		// 시큐리티는 기본적으로 CSRF 공격을 막기 위해 POST 요청을 차단함
		// POST 요청을 받기 위해 CSRF 보호 비활성화
		http.csrf().disable();
		
		/* API 로그인 필터 */
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
//		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login");	
		ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", memberService());	

		//로그인 필터에 인증매니저 주입
		apiLoginFilter.setAuthenticationManager(authenticationManager);
		
		// UsernamePasswordFilter는 폼로그인에서 사용하는 필터이다
		// apiLoginFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정한다
		 http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);

		/* API 체크 필터 */
		// apiCheckFilter가 UsernamePasswordFilter보다 먼저 실행되도록 설정
		http.addFilterBefore(apiCheckFilter(), 
							UsernamePasswordAuthenticationFilter.class); 
		 
		return http.build();
	}

}
