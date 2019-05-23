package com.sensedia.register.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sensedia.register.exception.MessageError;
import com.sensedia.register.exception.Messages;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MessageError> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
    	return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new MessageError(Messages.REQUIRED_FIELD, ex.getParameterName()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Void> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Void> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
