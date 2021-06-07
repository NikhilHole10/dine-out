package com.spring.dineout.service;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.RefreshToken;
import com.spring.dineout.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	
	public RefreshToken generateRefreshToken() {
		
		RefreshToken refreshToken =  new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		
		return refreshTokenRepository.save(refreshToken);
	}
	
	public void validateRefreshToken(String token) {
		refreshTokenRepository.findByToken(token)
		.orElseThrow(()-> new DineOutException("Invalid refresh token"));
	}
	
	public void deleteByRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}
}
