/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.repository.CustomerRepository;

/**
 * 
 * Provide authentication token for user service level.
 */
@Component(value = "customerAuthenticationProvider")
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerAuthenticationProvider(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = authentication.getCredentials().toString();
		Customer customer = customerRepository.findByUserNameAndPassword(username, password);
		if (customer == null) {
			throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer, null, AuthorityUtils.createAuthorityList("CUSTOMER"));
		return token;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}