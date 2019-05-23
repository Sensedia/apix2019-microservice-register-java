package com.sensedia.register.exception;

import java.util.List;

public class PreconditionFailedException extends RuntimeException {

	private static final long serialVersionUID = 1028970515655206002L;
	
    private final List<MessageError> errors;

    public PreconditionFailedException(List<MessageError> errors) {
        this.errors = errors;
    }

    public List<MessageError> getErrors() {
        return errors;
    }

}
