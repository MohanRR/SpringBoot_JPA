/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oneg.whsquared.entity.Customer;
import com.oneg.whsquared.entity.User;
import com.oneg.whsquared.repository.CustomerRepository;
import com.oneg.whsquared.repository.UserRepository;

/**
 * Authenticate user by filtering request and response object.
 * 
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	ProdutAuthenticationProvider authenticationManager;

	@Autowired
	CustomerAuthenticationProvider authenticationProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			chain.doFilter(request, response);
			return;
		}
		if (httpRequest.getRequestURI().startsWith("/security") || 
                        httpRequest.getRequestURI().startsWith("/api/bulkupload/download") ||
                        httpRequest.getRequestURI().startsWith("/api/user/forgotpassword")) {
			chain.doFilter(request, response);
		} else {
			try {
				String authToken = httpRequest.getHeader("x-authorization");
				// String authToken = "Bearer
				// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOnsiaWQiOjEsImNyZWF0ZWRCeSI6bnVsbCwiY3JlYXRpb25UaW1lIjpudWxsLCJtb2RpZmllZEJ5IjpudWxsLCJtb2RpZmljYXRpb25UaW1lIjpudWxsLCJmaXJzdE5hbWUiOiJBbmJ1a2thbmkiLCJsYXN0TmFtZSI6IkciLCJ1c2VyTmFtZSI6ImFuYnUiLCJwYXNzd29yZCI6bnVsbCwiZW1haWwiOiJqb2VAZ21haWwuY29tIiwiYXZhdGFyIjpudWxsLCJ1c2VyQWRkcmVzc0xpc3QiOm51bGwsInZlbmRvckxpc3QiOm51bGwsInBhc3N3b3JkVmVyaWZpY2F0aW9uTGlzdCI6bnVsbCwicm9sZSI6bnVsbH0sImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTQ3NjUyMjk5NTg5MCwiZXhwIjoyODQ5MTk3MzMxfQ.HGAWu5zeARJnw8lKaD9NKlyBscCrjuPVDDAuR1tNiqhMaXuj-jEWfeJYi6VJ5JnA9shBR1OzA5ZDDXl6lq15bQ";
				if (authToken == null || !(authToken.startsWith("Bearer ") || authToken.startsWith("WHREST "))) {
					throw new Exception("No JWT token found in request headers");
				}

				if (authToken != null && authToken.startsWith("Bearer ")) {

					// authToken = authToken.substring(7);
					String userName = jwtUtil.getUserName(authToken);

					if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						if (!jwtUtil.isTokenExpired(authToken)) {
							// httpResponse.setHeader("X-Authorization",
							// jwtUtil.refreshToken(authToken));
							List<User> users = userRepository.findByUserName(userName);
							User user = null;
							if (!users.isEmpty()) {
								user = users.get(0);
							}
							// Customer customer = userRepository.fin
							if (user != null) {
								UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(userName, user.getPassword());
								Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);

								if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
									throw new Exception("Unable to authenticate Domain User for provided credentials");
								}
								SecurityContextHolder.getContext().setAuthentication(responseAuthentication);
							}
						}
					}
				} else if (authToken != null && authToken.startsWith("WHREST ")) {
					String userName = jwtUtil.getUserName(authToken);

					if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						if (!jwtUtil.isTokenExpired(authToken)) {
							Customer customer = customerRepository.findCustomerByUserName(userName);
							if (customer != null) {
								UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(userName, customer.getPassword());
								Authentication responseAuthentication = authenticationProvider.authenticate(requestAuthentication);

								if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
									throw new Exception("Unable to authenticate Domain User for provided credentials");
								}
								SecurityContextHolder.getContext().setAuthentication(responseAuthentication);
							}
						}
					}
				}
			} catch (AuthenticationException authenticationException) {
				// SecurityContextHolder.clearContext();
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
				return;
			} catch (Exception ex) {
				ex.printStackTrace();
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
				return;
			}

			chain.doFilter(request, response);
		}
	}

}
