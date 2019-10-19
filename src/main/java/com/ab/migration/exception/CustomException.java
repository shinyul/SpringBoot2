package com.ab.migration.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = 7699099801384151799L;
	
	private HttpStatus httpStatus;
	
	public CustomException() {
        super();
    }
 
    public CustomException(Throwable e) {
        super(e);
    }
    
    public CustomException(Throwable e, HttpStatus httpStatus) {
        super(e);
    }
 
    public CustomException(String errorMessge, HttpStatus httpStatus) {
    	super(errorMessge);
        this.httpStatus = httpStatus;
    }
 
    public CustomException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }
    
    public CustomException(String errorMessge, Throwable e, HttpStatus httpStatus) {
        super(errorMessge, e);
        this.httpStatus = httpStatus;
    }
 
    public HttpStatus getHttpStatus() {
        return httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus ;
    }
    
    public Map<String,Object> getExceptionResposeBody() {
    	
    	Map<String, Object> erb = new HashMap<String, Object>();
    	erb.put("msg", this.getMessage());
    	erb.put("status", this.getHttpStatus().value());
    	
    	return erb;
    }
}
