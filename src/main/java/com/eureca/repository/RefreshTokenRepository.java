package com.eureca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureca.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	 Optional<RefreshToken> findByToken(String token);

	 /**
	  * 
	  * @param username
	  */
	 void deleteByUsername(String username);

}
