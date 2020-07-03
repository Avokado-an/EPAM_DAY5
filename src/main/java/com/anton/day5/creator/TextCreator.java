package com.anton.day5.creator;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.reader.ConsoleReader;
import com.anton.day5.reader.CustomFileReader;

public class TextCreator {
    public String createTextFromFile(String path) throws ProgramException {
        CustomFileReader reader = new CustomFileReader();
        return reader.readData(path);
    }

    public String createTextFromConsole() {
        ConsoleReader reader = new ConsoleReader();
        return reader.inputString();
    }
}
