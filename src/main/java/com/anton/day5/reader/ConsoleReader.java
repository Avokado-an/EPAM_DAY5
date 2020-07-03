package com.anton.day5.reader;

import java.util.Scanner;

public class ConsoleReader {
    public String inputString() {
        System.out.println("Press enter when you finish writing");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
