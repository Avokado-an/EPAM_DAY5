package com.anton.day5.reader;

import com.anton.day5.exception.ProgramException;

import java.io.FileInputStream;
import java.io.IOException;

public class CustomFileReader {
    public String readData(String path) throws ProgramException {
        if (path == null) {
            throw new ProgramException();
        }
        StringBuilder resultingArr;
        try (FileInputStream fin = new FileInputStream(path)) {
            int i;
            resultingArr = new StringBuilder();
            while ((i = fin.read()) != -1) {
                resultingArr.append((char) i);
            }
        } catch (IOException ex) {
            throw new ProgramException("File reader Exception", ex);
        }
        return resultingArr.toString();
    }
}
