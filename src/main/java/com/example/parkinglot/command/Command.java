package com.example.parkinglot.command;

/**
 * The Command functional interface
 *
 * @author ranjeet
 */
@FunctionalInterface
public interface Command {

    public Object apply(String... values);
}
