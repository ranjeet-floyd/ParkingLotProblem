package com.gojek.parkinglot.command.factory;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CreateParkingLotCommand;
import com.gojek.parkinglot.command.ParkCommand;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Command pattern
 *
 * @author ranjeet
 */
public class CommandFactory {

    private final Map<String, Command> commands;
    private static ParkingLotFactory parkingLotFactory;

    public CommandFactory() {
        this.commands = new HashMap<>();
    }

    private void addCommand(final String name, final Command command) {
        commands.put(name, command);
    }

    public <T> T executeCommand(String... command) {
        if (commands.containsKey(command[0])) {
            String[] values = Arrays.copyOfRange(command, 1, command.length);
            return commands.get(command[0]).apply(values);
        }
        throw new UnsupportedOperationException("command not found :" + command[0]);
    }

    /* Factory pattern */
    public static CommandFactory init() {
        final CommandFactory cf = new CommandFactory();

        // commands are added here 
        Command command = new CreateParkingLotCommand();
        cf.addCommand("create_parking_lot", command);
        cf.addCommand("park", new ParkCommand(parkingLotFactory));
        //leave
        cf.addCommand("leave", command);
        return cf;
    }

    public static void main(String[] args) {

        final CommandFactory cf = CommandFactory.init();
        Object t = cf.executeCommand(new String[]{"create_parking_lot", "6"});
    }

}
