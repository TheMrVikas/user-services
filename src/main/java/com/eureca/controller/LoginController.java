package com.eureca.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureca.dto.LoginRequestDTO;
import com.eureca.entity.RefreshToken;
import com.eureca.repository.RefreshTokenRepository;
import com.eureca.sequrity.JwtSecurity;
import com.eureca.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

	private AuthenticationManager authenticationManager;
	private JwtSecurity jwtService;
	private RefreshTokenService refreshTokenService;
	private RefreshTokenRepository refreshTokenRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

		String token = jwtService.generateToken(req.getUsername());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(req.getUsername());

		return ResponseEntity.ok(Map.of("accessToken", token, "refreshToken", refreshToken.getToken()));
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {

		String requestToken = request.get("refreshToken");

		RefreshToken refreshToken = refreshTokenRepository.findByToken(requestToken)
				.map(refreshTokenService::verifyExpiration)
				.orElseThrow(() -> new RuntimeException("Invalid refresh token"));

		String newAccessToken = jwtService.generateToken(refreshToken.getUsername());

		return ResponseEntity.ok(Map.of("accessToken", newAccessToken, "refreshToken", requestToken));
	}
}
