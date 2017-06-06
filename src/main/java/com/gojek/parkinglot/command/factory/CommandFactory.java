package com.gojek.parkinglot.command.factory;

import com.gojek.parkinglot.command.Command;
import java.util.HashMap;
import java.util.Map;

/**
 * Command pattern
 *
 * @author ranjeet
 */
public class CommandFactory {

    private final Map<String, Command> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
    }

    private void addCommand(final String name, final Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name) {
        if (commands.containsKey(name)) {
            commands.get(name).apply();
        }
    }

    /* Factory pattern */
    public static CommandFactory init() {
        final CommandFactory cf = new CommandFactory();

        // commands are added here 
        cf.addCommand("create_parking_lot", () -> System.out.println("create parking lot"));

        return cf;
    }

}
