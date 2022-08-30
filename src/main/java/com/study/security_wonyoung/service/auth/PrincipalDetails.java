package com.study.security_wonyoung.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.study.security_wonyoung.domain.user.User;

import lombok.Data;

@Data								  // 일반로그인	  Auth2 로그인
public class PrincipalDetails implements UserDetails, OAuth2User {
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attribute;
	
	// 일반로그인때 필요한 것.
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// OAuth2때 필요한 것.
	public PrincipalDetails(User user, Map<String, Object> attribute) {
		this.user = user;
		this.attribute = attribute;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
//		List<String> roleList = user.getUserRoles();
//		
//		for(String role : roleList) {
//			GrantedAuthority authority = new GrantedAuthority() {
//				private static final long serialVersionUID = 1L;
//
//				@Override
//				public String getAuthority() {
//					return role;
//				}
//			};
//			
//			grantedAuthorities.add(authority);
//		}
		
		user.getUserRoles().forEach(role -> {
			grantedAuthorities.add(() -> role);
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getUser_password();
	}

	@Override
	public String getUsername() {
		return user.getUser_id();
	}

	/*
	 * 계정 만료 여부
	 * true: 만료 안됨
	 * false: 만료
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/*
	 * 계정 잠김 여부
	 * true: 잠기지 않음
	 * false: 잠김
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/*
	 * 비밀번호 만료 여부
	 * true: 만료 안됨
	 * false: 만료됨
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * 사용자 활성화 여부
	 * true: 활성화
	 * false: 비활성화
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attribute;
	}

	@Override
	public String getName() {
		return user.getUser_name();
	}

}