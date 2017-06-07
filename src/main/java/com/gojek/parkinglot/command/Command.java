package com.gojek.parkinglot.command;

/**
 * The Command functional interface
 *
 * @author ranjeet
 */
@FunctionalInterface
public interface Command {

    public <T> T apply(Object... values) throws Exception;
}
