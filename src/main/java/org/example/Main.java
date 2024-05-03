package org.example;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        // Welcome
        System.out.println("Welcome to the CLIculator, how can I help you today? \n");

        // Initialize
        Calculator calc = new Calculator();
        Scanner myScanner = new Scanner(System.in);

        // User input
        System.out.println("Enter two integers to add together:");

        // Check validity
        try {
            int a = myScanner.nextInt();
            int b = myScanner.nextInt();
            System.out.println("The sum of the numbers " + a + " + " + b + " = " + calc.add(a,b));
        } catch (Exception e) {
            System.out.println("Error on input:");
            System.out.println(e);
            exit(1);
        }
    }
}