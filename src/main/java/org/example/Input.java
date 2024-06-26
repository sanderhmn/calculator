package org.example;

import java.util.Scanner;

public class Input {
    Scanner scanner;

    public Input (Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput() {
        System.out.println("Please enter your calculation:");
        return scanner.nextLine();
    }
}
