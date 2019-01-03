package com.fizzbuzz.exception;

/**
 *
 * @author hugo
 */
public class FizzBuzzException extends Exception {
    
    public FizzBuzzException(String message) {
        super(message);
    }    
    public FizzBuzzException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
