package com.study.security_wonyoung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.study.security_wonyoung.config.auth.AuthFailureHandler;

@EnableAsync //기존의 상속받은 WebSecurityConfigurerAdapter를 비활성 시키고 현재 시큐리티 설정을 따르겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	//암호화
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//기본설정
		http.csrf().disable(); 							// input 태그에 토큰 발행
		http.authorizeRequests() 						// 요청이 들어오면 인증을 거쳐라!!
			.antMatchers("/","/index","/mypage/**") 	// 우리가 지정한 요청
			.authenticated()							// 인증을 거쳐라
			.anyRequest() 								// 다른 모든요청들을
			.permitAll() 								// 권한을 부여하겠다.(인증을 거칠 필요없다)
			.and() 										//---------(((여기까지 한세트))))------------
			.formLogin() 								// 로그인 방식은 from로그인을 사용하겠다.
			.loginPage("/auth/signin") 					// 로그인 페이지는 해당 get요청을 통해 접근한다.(내가 만든 페이지 주소로 요청하게끔 유도)
			.loginProcessingUrl("/auth/signin")			// 로그인 요청(post요청)
			.failureHandler(new AuthFailureHandler()) 	// 예외처리 문자 만들기?
			.defaultSuccessUrl("/");					
	}
}
