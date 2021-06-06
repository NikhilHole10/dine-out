package com.spring.dineout.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.UserRegisterRequest;
import com.spring.dineout.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor

public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/userSignup")
	public ResponseEntity<String> signup(@RequestBody UserRegisterRequest userRegisterRequest) {
		authService.signupUser(userRegisterRequest);
		return new ResponseEntity<>("User Registration successful",HttpStatus.OK);
	}
}
