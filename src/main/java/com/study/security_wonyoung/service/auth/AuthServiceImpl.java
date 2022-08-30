package com.study.security_wonyoung.service.auth;

import org.springframework.stereotype.Service;

import com.study.security_wonyoung.domain.user.UserRepository;
import com.study.security_wonyoung.wep.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	
	
	@Override
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception {
		
		return userRepository.findUserByUsername(usernameCheckReqDto.getUsername()) == null;
	}

	@Override
	public boolean signup() throws Exception{
		return false;
	}

}



