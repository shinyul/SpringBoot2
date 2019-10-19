package com.ab.migration.firebaseIdToken;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth/fire/token")
public class FirebaseIdTokenController {
	
	
	//firebase Token + oauth2 커스텀시 사용 예정 지금은 사용하지 않음. 테스트용으로만 사용했음. 
	//Security 설정으로 제외 했기 때문에 호출 안됨. 
	@PostMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public ResponseEntity<String> firebaseIdTokenVerification( @RequestHeader(value="TOKEN_ID") String idToken ) throws FirebaseAuthException {
		FirebaseToken decodedToken = null;
		log.debug("토큰을 주세요. " ,idToken);
		decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
		String uid = decodedToken.getUid();
		
		log.debug("접속한 사용자의 UID 는?  ::  " + (uid == null ? "없음" : uid) );
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
