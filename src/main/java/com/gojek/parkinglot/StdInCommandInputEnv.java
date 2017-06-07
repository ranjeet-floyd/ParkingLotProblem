package com.gojek.parkinglot;

import java.io.InputStream;

/**
 *
 * @author ranjeet
 */
public class StdInCommandInputEnv implements CommandInputEnv {

    private final InputStream inputStream;

    public StdInCommandInputEnv() {
        this.inputStream = System.in;
    }

    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }

}
