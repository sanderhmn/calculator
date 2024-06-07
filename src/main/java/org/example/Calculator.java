package org.example;

import static java.lang.System.exit;

public class Calculator {
    Input input;
    Interpreter interpreter;

    public Calculator(Input input, Interpreter interpreter) {
        this.input = input;
        this.interpreter = interpreter;
    }

    public void performCalculation() {
        String prompt = input.getInput();
        try {
            double result = interpreter.buildTree(prompt);
            System.out.println("The result: " + result);
        } catch (RuntimeException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
            exit(1);
        }
    }
}
