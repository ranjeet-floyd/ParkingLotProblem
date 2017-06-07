package com.gojek.parkinglot.command.factory;

import com.gojek.parkinglot.ParkingLotFactory;
import com.gojek.parkinglot.command.Command;
import com.gojek.parkinglot.command.CreateParkingLotCommand;
import com.gojek.parkinglot.command.LeaveCommand;
import com.gojek.parkinglot.command.ParkCommand;
import com.gojek.parkinglot.command.RegNumsForCarsWithColourCommand;
import com.gojek.parkinglot.command.SlotNumForRegisNumberCommand;
import com.gojek.parkinglot.command.SlotNumsForCarsWithColourCommand;
import com.gojek.parkinglot.command.StatusCommand;
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

    public CommandFactory() {
        this.commands = new HashMap<>();
    }

    private void addCommand(final String name, final Command command) {
        commands.put(name, command);
    }

    public <T> T executeCommand(Object... command) throws Exception {
        if (commands.containsKey((String) command[0])) {
            Object[] values = Arrays.copyOfRange(command, 1, command.length);
            return commands.get((String) command[0]).apply(values);
        }
        throw new UnsupportedOperationException("Operation not supported :" + command[0]);
    }

    /* Factory pattern */
    public static CommandFactory init() {
        final CommandFactory cf = new CommandFactory();

        // commands are added here 
        cf.addCommand("create_parking_lot", new CreateParkingLotCommand());
        cf.addCommand("park", new ParkCommand());
        cf.addCommand("leave", new LeaveCommand());
        cf.addCommand("status", new StatusCommand());
        cf.addCommand("registration_numbers_for_cars_with_colour", new RegNumsForCarsWithColourCommand());
        cf.addCommand("slot_numbers_for_cars_with_colour", new SlotNumsForCarsWithColourCommand());
        cf.addCommand("slot_number_for_registration_number", new SlotNumForRegisNumberCommand());
        return cf;
    }

    public static void main(String[] args) throws Exception {

        final CommandFactory cf = CommandFactory.init();
        ParkingLotFactory parkingLotFactory = cf.executeCommand("create_parking_lot", "6");
        cf.executeCommand("park", parkingLotFactory, "KA-01-HH-3141", "White");
        cf.executeCommand("park", parkingLotFactory, "KA-01-HH-1235", "White");
        cf.executeCommand("park", parkingLotFactory, "KA-01-HH-1236", "White");
//        cf.executeCommand("leave", parkingLotFactory,"1");
        cf.executeCommand("status", parkingLotFactory);
        cf.executeCommand("registration_numbers_for_cars_with_colour", parkingLotFactory, "White");
        cf.executeCommand("slot_numbers_for_cars_with_colour", parkingLotFactory, "White");
        cf.executeCommand("slot_number_for_registration_number", parkingLotFactory, "KA-01-HH-3141");
        cf.executeCommand("slot_number_for_registration_number", parkingLotFactory, "MH-04-AY-1111");
    }

}
