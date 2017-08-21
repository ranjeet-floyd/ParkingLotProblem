package com.example.parkinglot.exception;

/**
 * If given carInfo is not found.
 *
 * @author ranjeet
 */
public class NoSuchCarFoundException extends Exception {

    public NoSuchCarFoundException(String string) {
        super(string);
    }

}
