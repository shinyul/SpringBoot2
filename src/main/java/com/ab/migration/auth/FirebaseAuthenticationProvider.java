package com.ab.migration.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ab.migration.exception.CustomException;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

//	@Autowired
//	private UserDetailsService userDetailsService;

	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public Authentication authenticate(Authentication authentication) throws CustomException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;

		
		FirebaseTokenHolder tokenHolder = ((FirebaseTokenHolder)authenticationToken.getCredentials());
		
		UserDetails details =User.builder().username(authenticationToken.getName()).password(((FirebaseTokenHolder)authenticationToken.getCredentials()).getUid())
									.roles(tokenHolder.getRoles().toArray(new String[]{})).build()  ;
		
		if (details == null) {
			throw new CustomException("User information does not exist. Please check again.", HttpStatus.UNAUTHORIZED);
		}

		authenticationToken = new FirebaseAuthenticationToken(details, authentication.getCredentials(), details.getAuthorities());

		return authenticationToken;
	}

}
