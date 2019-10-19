package com.ab.migration.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ab.migration.auth.FirebaseAuthenticationToken;
import com.ab.migration.auth.FirebaseTokenHolder;
import com.ab.migration.common.FirebaseUtils;
import com.ab.migration.exception.CustomException;

public class FirebaseFilter extends OncePerRequestFilter {

	private static String HEADER_NAME = "TOKEN_ID";
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String firebaseToken = request.getHeader(HEADER_NAME);
		if ( firebaseToken == null || firebaseToken.trim().isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		} else {
			try {
				
				FirebaseTokenHolder holder = new FirebaseUtils().parseToken(firebaseToken);
				
				Authentication auth = new FirebaseAuthenticationToken(holder.getEmail(), holder);
				SecurityContextHolder.getContext().setAuthentication(auth);
				
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage(),HttpStatus.UNAUTHORIZED);
			}
		}
	}
	
	
}
