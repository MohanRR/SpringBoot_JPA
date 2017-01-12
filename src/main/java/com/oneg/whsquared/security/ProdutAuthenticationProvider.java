/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.security;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.UserRepository;

/**
 * 
 * Provide authentication token for user service level.
 */
@Component(value = "authenticationProvider")
public class ProdutAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	public ProdutAuthenticationProvider(UserRepository userProfileRepository) {
		this.userRepository = userProfileRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = authentication.getCredentials().toString();
		List<User> users = userRepository.findByUserName(username);
		User user = null;
		if (!users.isEmpty()) {
			user = users.get(0);
		}

		if (user == null) {
			throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
		}

		String suppliedPasswordHash = DigestUtils.md5Hex(authentication.getCredentials().toString());

		if (!user.getPassword().equals(authentication.getCredentials().toString()) && !user.getPassword().equals(suppliedPasswordHash)) {
			throw new BadCredentialsException("Invalid credentials");
		}

		// UsernamePasswordAuthenticationToken token = new
		// UsernamePasswordAuthenticationToken(profile, null,
		// profile.getAuthorities());

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList("USERS"));

		return token;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}