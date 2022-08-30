package com.study.security_wonyoung.wep.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grant/test")
public class GrantTestController {
		
	@GetMapping("/user")
	public ResponseEntity<?> getUserData() {
			
		return ResponseEntity.ok("ROLE_USER 권한을 가지고 있음.");
	}
	
	@GetMapping("/manger")
	public ResponseEntity<?> getMANGERData() {
			
		return ResponseEntity.ok("ROLE_MANGER 권한을 가지고 있음.");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<?> getAdminData() {
			
		return ResponseEntity.ok("ROLE_ADMIN 권한을 가지고 있음.");
	}
	
}
