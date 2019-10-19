package com.ab.migration.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ab.migration.exception.CustomException;
import com.google.firebase.auth.FirebaseAuthException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin
@ControllerAdvice
public class ResponseExceptionHandler {
	

	@ExceptionHandler( CustomException.class )
	protected ResponseEntity<Map<String, Object>> customExceiptinHandler (CustomException e) {
		log.error("CustomExceiptin Error Message : ", e);
		
		return new ResponseEntity<>(e.getExceptionResposeBody(),e.getHttpStatus()); 
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<String> vlied (MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException Error Message :", e);
		
		return new ResponseEntity<>("Put the return data",HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(FirebaseAuthException.class)
	protected ResponseEntity<String> firebaseAuthHandler (FirebaseAuthException e) {
		log.error("FirebaseAuthException Error Message :", e);
		
		return new ResponseEntity<>("firebaseAuthHandler Put the return data", HttpStatus.FORBIDDEN); 
	}
	
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<String> allException (NullPointerException e) {
		log.error("NullPointerException Error Message :", e);
		
		return new ResponseEntity<>("firebaseAuthHandler Put the return data",HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<String> allException (MissingRequestHeaderException e) {
		log.error("MissingRequestHeaderException Error Message : ", e);
		
		return new ResponseEntity<>("firebaseAuthHandler Put the return data",HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<String> requestMethodNotSupported (HttpRequestMethodNotSupportedException e) {
		log.error("HttpRequestMethodNotSupportedException Error Message : ", e);
		
		return new ResponseEntity<>("HttpRequestMethodNotSupportedException Put the return data",HttpStatus.METHOD_NOT_ALLOWED); 
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<String> requestMethodNotSupported (HttpMessageNotReadableException e) {
		log.error("HttpMessageNotReadableException Error Message : ", e);
		
		return new ResponseEntity<>("requestMethodNotSupported",HttpStatus.METHOD_NOT_ALLOWED); 
	}
	
}
