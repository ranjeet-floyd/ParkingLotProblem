package com.example.parkinglot;

import com.example.parkinglot.command.factory.CommandFactory;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author ranjeet
 */
public class APP {

    public static void main(String[] args) throws Exception {
        InputStream inputStream = CommandEnvAbstractFactory.getCommandInputEnv(args).getInputStream();
        Scanner readInput = new Scanner(inputStream);
        final CommandFactory cf = CommandFactory.init();
        while (readInput.hasNext()) {
            String[] commandWithParams = readInput.nextLine().split(" ");
            cf.executeCommand(commandWithParams);
        }

    }
}
