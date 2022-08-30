package com.study.security_wonyoung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.study.security_wonyoung.config.auth.AuthFailureHandler;
import com.study.security_wonyoung.handler.aop.annotation.Log;
import com.study.security_wonyoung.service.auth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity //기존의 WebSecurityConfigurerAdapter를 비활성 시키고 현재 시큐리티 설정을 따르겠다.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CorsFilter corsFilter;
	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Log
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers()
			.frameOptions()
			.disable();
//		http.addFilter(corsFilter); //cors 인증을 하지 않겠다.
		http.authorizeRequests()
			.antMatchers("/api/v1/grant/test/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.antMatchers("/api/v1/grant/test/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.antMatchers("/notice/addition", "/notice/modification/**")
			//.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.hasRole("ADMIN")
			
			.antMatchers("/api/v1/grant/test/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			
			.antMatchers("/", "/index", "/mypage/**")	// 우리가 지정한 요청
			.authenticated()	// 인증을 거쳐라
			
			.anyRequest()										// 다른 모든요청은
			.permitAll()										// 모두 접근 권한을 부여하겠다.
			
			.and()
			
			.formLogin()										// 로그인 방식은 form로그인을 사용하겠다.
			.loginPage("/auth/signin") 							// 로그인 페이지는 해당 get요청을 통해 접근한다.
			.loginProcessingUrl("/auth/signin")					// 로그인 요청(post요청)
			.failureHandler(new AuthFailureHandler())
			
			.and()
			
			.oauth2Login()
			.userInfoEndpoint()
			/*
			 * 1. google, naver, kakao 로그인 요청 -> 코드를 발급하여줌.
			 * 2. 발급받은 코드를 가진 상태로 권한요청(토큰발급요청)을 함.
			 * 3. 스코프에 등록된 프로필 정보를 가져올 수 있게된다.
			 * 4. 해당 정보를 시큐리티의 객체로 전달받음.
			 * 
			 */
			.userService(principalOauth2UserService)
			
			.and()
			
			.defaultSuccessUrl("/index");
	}
	
}


