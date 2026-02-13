package com.eureca.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureca.entity.RefreshToken;
import com.eureca.repository.RefreshTokenRepository;
import com.eureca.service.RefreshTokenService;

import jakarta.transaction.Transactional;
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository repository;
	
	@Transactional
	@Override
	public RefreshToken createRefreshToken(String username) {
		repository.deleteByUsername(username); // rotate

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(
                Instant.now().plus(7, ChronoUnit.DAYS));

        return repository.save(refreshToken);
	}

	 public RefreshToken verifyExpiration(RefreshToken token) {

	        if (token.getExpiryDate().isBefore(Instant.now())) {
	            repository.delete(token);
	            throw new RuntimeException("Refresh token expired");
	        }

	        return token;
	    }
}
