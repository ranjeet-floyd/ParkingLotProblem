package com.example.parkinglot.exception;

/**
 * If No space left in parking lot.
 *
 * @author ranjeet
 */
public class NoSpaceException extends Exception {
    
    public NoSpaceException(String string) {
        super(string);
    }
    
}
