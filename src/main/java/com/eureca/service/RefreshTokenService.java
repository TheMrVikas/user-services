package com.eureca.service;

import com.eureca.entity.RefreshToken;

public interface RefreshTokenService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	public RefreshToken createRefreshToken(String username);
	/**
	 * 
	 * @param token
	 * @return
	 */
	public RefreshToken verifyExpiration(RefreshToken token);

}
