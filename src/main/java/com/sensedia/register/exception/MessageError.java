package com.sensedia.register.exception;

import java.io.Serializable;

import com.sensedia.register.component.Dictionary;

public class MessageError implements Serializable {

	private static final long serialVersionUID = 4605241545816644847L;
	
	private String code;
    private String message;
    
    public MessageError() {
    	// empty constructor
    }

    public MessageError(String code, Object... args) {
        this.code = code;
        this.message = Dictionary.valueOf(code, args);
    }
    
    public String getCode() {
    	return code;
    }
    
    public void setCode(String code) {
		this.code = code;
	}
    
    public String getMessage() {
    	return message;
    }
    
    public void setMessage(String message) {
		this.message = message;
	}

}
