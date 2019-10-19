package com.ab.migration.auth;

import java.util.List;
import java.util.Map;

import com.google.firebase.auth.FirebaseToken;

import lombok.Getter;

public class FirebaseTokenHolder {
	private FirebaseToken token;
	@Getter
	private List<String> roles;
	
	@Getter
	private String firebaseToken;
	
	public FirebaseTokenHolder(FirebaseToken token) {
		this.token = token;
	}
	
	public FirebaseTokenHolder(FirebaseToken token, List<String> roles, String firebaseToken) {
		this.token = token;
		this.roles = roles;
		this.firebaseToken = firebaseToken;
	}

	public String getEmail() {
		return token.getEmail();
	}

	public String getIssuer() {
		return token.getIssuer();
	}

	public String getName() {
		return token.getName();
	}

	public String getUid() {
		return token.getUid();
	}
	
	public Map<String,Object> getClaims() {
		return token.getClaims();
	}

}
