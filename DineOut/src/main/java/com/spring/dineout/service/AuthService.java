package com.spring.dineout.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.AuthenticationResponse;
import com.spring.dineout.dto.RefreshTokenRequest;
import com.spring.dineout.dto.UserLoginRequest;
import com.spring.dineout.dto.UserRegisterRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.NotificationEmail;
import com.spring.dineout.model.User;
import com.spring.dineout.model.VerificationToken;
import com.spring.dineout.repository.UserRepository;
import com.spring.dineout.repository.VerificationTokenRepository;
import com.spring.dineout.security.JwtProvider;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;
	
	@Transactional
	public void signupUser(UserRegisterRequest  userRegisterRequest) {
		if(userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()) {
			throw  new DineOutException("Email "+userRegisterRequest.getEmail()+" already present");
		}
		
		
		User user = new User(
				userRegisterRequest.getName(),
				userRegisterRequest.getEmail(),
				userRegisterRequest.getContact_no(),
				passwordEncoder.encode(userRegisterRequest.getPassword()),
				userRegisterRequest.getCity(),
				Instant.now(),
				false,
				false,
				"USER"
				);
		userRepository.save(user);
		String token= generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please activate your account",user.getEmail(),"Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
	}
	
	private String generateVerificationToken(User user) {
		String verificationToken = UUID.randomUUID().toString();
		VerificationToken vt = new VerificationToken();
		vt.setToken(verificationToken);
		vt.setUser(user);
		verificationTokenRepository.save(vt);		
		return verificationToken;
		
	}

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken =  verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(()->new DineOutException("Token not found"));
		fetchUserAndEnable(verificationToken.get());
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getEmail();
		User user = userRepository.findByEmail(username).orElseThrow(()->new DineOutException("User with username"+username+ " does not exist"));
		user.setAccount_status(true);
		userRepository.save(user);
	}

	public AuthenticationResponse userLogin(UserLoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token =  jwtProvider.generateToken(authenticate);
		return  AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(loginRequest.getEmail())
				.build();
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(refreshTokenRequest.getUsername())
				.build();
	}

	
	
	
	
}
