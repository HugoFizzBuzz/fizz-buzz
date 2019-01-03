package com.fizzbuzz.exception;

/**
 *
 * @author hugo
 */
public class CustomException extends Exception {
    
    /*
    public CustomException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }
    */
    
    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
