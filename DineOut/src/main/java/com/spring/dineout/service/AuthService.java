package com.spring.dineout.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.UserRegisterRequest;
import com.spring.dineout.model.NotificationEmail;
import com.spring.dineout.model.User;
import com.spring.dineout.model.VerificationToken;
import com.spring.dineout.repository.UserRepository;
import com.spring.dineout.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	
	
	@Transactional
	public void signupUser(UserRegisterRequest  userRegisterRequest) {
		if(userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()) {
			throw new IllegalStateException("Email "+userRegisterRequest.getEmail()+" already present");
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
				"ROLE_USER"
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
	
	
}
