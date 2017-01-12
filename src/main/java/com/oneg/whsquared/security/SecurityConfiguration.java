/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration for page level to access
 * 
 */
// @Configuration
// public class SecurityConfiguration extends
// GlobalAuthenticationConfigurerAdapter {
//
// }

@EnableWebSecurity
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	ProdutAuthenticationProvider authenticationProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/security", 
                        "/Error/**",
                        "/Resources/**",
                        "/api/bulkupload/download/*",
                        "/api/user/forgotpassword");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().authenticationEntryPoint(unauthenticatedEntryPoint());
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/*.html",
                        "/favicon.ico",
                        "/api/bulkupload/download", 
                        "/api/user/forgotpassword").permitAll().antMatchers("/security/**", "*").permitAll().antMatchers("/api/**")
				.authenticated();
		http.csrf().disable();
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

		// Custom JWT based security filter
		http.addFilterAfter(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class).antMatcher("/api/**");
		// disable page caching
		http.headers().cacheControl();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403

			}
		};
	}

	@Bean
	public AuthenticationEntryPoint unauthenticatedEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
				// SC_UNAUTHORIZED returns a 401. It should really be an
				// SC_UNAUTHENTICTATED instead
				response.sendError(HttpServletResponse.SC_ACCEPTED); // 401
			}
		};
	}

	@Bean
	public AuthenticationFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationFilter authenticationTokenFilter = new AuthenticationFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}
}
