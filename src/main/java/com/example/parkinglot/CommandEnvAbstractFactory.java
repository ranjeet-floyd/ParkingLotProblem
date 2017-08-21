package com.example.parkinglot;

/**
 *
 * @author ranjeet
 */
public class CommandEnvAbstractFactory {

    private CommandEnvAbstractFactory() {
    }

    public static CommandInputEnv getCommandInputEnv(String[] args) {
        if (args.length == 1) {
            return new FileCommandInputEnv(args[0]);
        }
        return new StdInCommandInputEnv();
    }

}
