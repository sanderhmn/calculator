package org.example;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        // Welcome
        System.out.println("Welcome to the CLIculator, how can I help you today? \n");

        // Initialize objects
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Interpreter interpreter = new Interpreter();

        // Create calculator and do calculation
        Calculator calculator = new Calculator(input, interpreter);
        calculator.performCalculation();

        exit(0);
    }
}