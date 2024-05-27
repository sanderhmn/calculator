package org.example;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        // Welcome
        System.out.println("\n" + "Welcome to the CLIculator, how can I help you today?" + "\n");
        System.out.println("For the current version I require spaces between the numbers and operators");
        System.out.println("So please use the following notation: 3 + 5 - 3 * 2");

        // Initialize objects
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Interpreter interpreter = new Interpreter();

        // Create calculator and do calculation
        Calculator calculator = new Calculator(input, interpreter);
        double startTime = System.currentTimeMillis();

        // Shutdown after 10 mins
        while (System.currentTimeMillis() - startTime < 600000) {
            calculator.performCalculation();
        }

        scanner.close();
        exit(0);
    }
}