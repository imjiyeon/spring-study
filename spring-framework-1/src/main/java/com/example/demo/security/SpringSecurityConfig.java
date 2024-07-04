package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 스프링 시큐리티 설정 클래스
 * */
@Configuration
@EnableWebSecurity //스프링 MVC와 시큐리티를 결합
public class SpringSecurityConfig{
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //인증 필터 등록
		
		/*crsf 비활성화 */
		http
			.csrf().disable(); //csrf는 get을 제외하여 상태값을 위조(변경)할 수있는 post,put,delete 메소드를 막음
		
		/* 회원가입과 로그인은 아무나 접근 가능 */
		http
			.authorizeRequests()
				.antMatchers("/register").permitAll() //로그인을 하지않은 익명의 사용자도 접근 허용
				.antMatchers("/login").permitAll();
		
		/* 메뉴별 접근제한 */
		http
			.authorizeRequests()
				.antMatchers("/").authenticated()  //메인화면 - 로그인한 사용자일 경우 접근 허용
				.antMatchers("/board/*").hasAnyRole("ADMIN","USER")  //관리자 또는 사용자일경우 접근허용, ROLE_는 prefix로 붙는다
				.antMatchers("/member/*").hasRole("ADMIN"); //관리자일경우 접근허용

		/* 로그인 */
		http
	        .formLogin() //Form 로그인 인증 기능이 작동함
		        .loginPage("/customLogin") //사용자 정의 로그인 페이지
		        .loginProcessingUrl("/login") //로그인 처리 주소
		        .defaultSuccessUrl("/", true) //로그인 성공 후 이동 페이지
		        .failureHandler(new LoginFailHandler()) //로그인 실패 처리 핸들러
		        .permitAll(); //모든 사용자 접근 허용
		
		http
			.exceptionHandling().accessDeniedPage("/accessError"); //접근제한 처리 페이지
		
		/* 로그아웃 */
		http
			.logout()//로그아웃 처리
		        .logoutUrl("/customLogout")// 로그아웃 처리 URL
		        .logoutSuccessUrl("/customLogin")//로그아웃 성공 후 이동페이지
		        .deleteCookies("JSESSIONID"); //로그아웃 후 쿠키 삭제
 
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() { //인증처리 Provider 등록
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(detailsServiceImpl); //사용자정의 인증서비스 등록
	    provider.setPasswordEncoder(new BCryptPasswordEncoder()); //패스워드인코더 등록, 스프링이 로그인과정에서 패스워드를 암호화해서 비교한다
	    provider.setHideUserNotFoundExceptions(false); 
	    //스프링은 기본적으로 로그인을 실패하면, 정확하게 아이디가 잘못됬는지 패스워드가 틀렸는지 알려주지않고 BadCredentialsException (인증실패)로 처리한다
	    //정확하게 아이디가 존재하지않을경우에는 UserNotFoundException 를 발생하도록 설정한다
	    
	    return provider;
	}
	

}
