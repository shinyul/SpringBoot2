package com.ab.migration.common;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.ab.migration.auth.FirebaseTokenHolder;
import com.ab.migration.exception.CustomException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseUtils {

	public FirebaseTokenHolder parseToken(String firebaseToken) {
		
		if ( firebaseToken == null || firebaseToken.trim().isEmpty()) {
			throw new CustomException("There is no Firebase token.", HttpStatus.UNAUTHORIZED);
		}
		try {
			FirebaseToken verifyToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
			// firebaseToken 확인용으로 추가. 추후 삭제해도 무방함.	
			return new FirebaseTokenHolder(verifyToken, setRolesAuthority(), firebaseToken);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Firebase token is not valid. Please check again", e, HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	public List<String> setRolesAuthority() {
				
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		roles.add("MANAGER");
		
		return roles; 
	}
}
