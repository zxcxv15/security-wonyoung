package com.study.security_wonyoung.wep.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.study.security_wonyoung.domain.user.User;

import lombok.Data;

@Data
public class SignupReqDto {
	@NotBlank
	@Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다.")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,12}$")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$")
	private String password;
	
	@AssertTrue(message = "아이디 중복확인이 되지 않았습니다.")
	private boolean checkUsernameFlag;
	
	
	public User toEntity() {
		return User.builder()
				.user_name(name)
				.user_email(email)
				.user_id(username)
				.user_password(new BCryptPasswordEncoder().encode(password))
				.user_roles("ROLE_USER")
				.build();
	}
	
}
