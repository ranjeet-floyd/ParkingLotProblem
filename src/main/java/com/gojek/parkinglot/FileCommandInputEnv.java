package com.gojek.parkinglot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author ranjeet
 */
public class FileCommandInputEnv implements CommandInputEnv {

    private final String fileName;

    public FileCommandInputEnv(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File not found @ :" + fileName, ex);
        }
    }

}
