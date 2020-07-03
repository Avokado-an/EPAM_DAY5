package com.anton.day5.reader;

import java.util.Scanner;

public class ConsoleReader {
    public String inputString() {//todo add private methods not ot duplicate them
        System.out.println("Press enter when you finish writing");
        Scanner scanner = new Scanner(System.in); //todo super class for default stuff
        return scanner.nextLine();
    }
}
