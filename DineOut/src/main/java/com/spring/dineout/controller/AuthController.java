package com.spring.dineout.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.AuthenticationResponse;
import com.spring.dineout.dto.RefreshTokenRequest;
import com.spring.dineout.dto.RestoAdminLoginRequest;
import com.spring.dineout.dto.RestoAdminRegisterRequest;
import com.spring.dineout.dto.UserLoginRequest;
import com.spring.dineout.dto.UserRegisterRequest;
import com.spring.dineout.service.AuthService;
import com.spring.dineout.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor

public class AuthController {
	
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;
	
	@PostMapping("/usersignup")
	public ResponseEntity<String> signup(@RequestBody UserRegisterRequest userRegisterRequest) {
		authService.signupUser(userRegisterRequest);
		return new ResponseEntity<>("User Registration successful",HttpStatus.OK);
	}
	
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account activated successfully",HttpStatus.OK);
	}
	
	@PostMapping("/userlogin")
	public AuthenticationResponse userLogin(@RequestBody UserLoginRequest userLoginRequest) {
		return authService.userLogin(userLoginRequest);
	}
	
	@PostMapping("/refresh/token")
	public AuthenticationResponse refreshTokens(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return authService.refreshToken(refreshTokenRequest);
	}
	
	@PostMapping("/logout")
	public  ResponseEntity<String> logout(@RequestBody RefreshTokenRequest refreshTokenRequest){
		refreshTokenService.deleteByRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully");
	}
	
	@PostMapping("/restoadminsignup")
	public ResponseEntity<String> restoAdminSignup(@RequestBody UserRegisterRequest userRegisterRequest) {
		authService.SignUpRestoAdmin(userRegisterRequest);
		return new ResponseEntity<>("User Registration successful",HttpStatus.OK);
	}
	
	
	@PostMapping("/restoadminlogin")
	public AuthenticationResponse restoAdminLogin(@RequestBody UserLoginRequest userLoginRequest) {
		return authService.userLogin(userLoginRequest);
	}
	
}
