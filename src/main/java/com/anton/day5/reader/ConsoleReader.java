package com.anton.day5.reader;

import java.util.Scanner;

public class ConsoleReader {
    public String inputString() {//todo add private methods not ot duplicate them
        Scanner scanner = new Scanner(System.in); //todo super class for default stuff
        return scanner.nextLine();
    }
}
