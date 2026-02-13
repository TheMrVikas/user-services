package com.eureca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eureca.entity.UserLoginEntity;
import com.eureca.repository.UserLoginRepository;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

	@Autowired
    private UserLoginRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserLoginEntity user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return User.withUsername(user.getUserName()).password(user.getPazzword()).authorities("ROLE_USER").build();
	}
}