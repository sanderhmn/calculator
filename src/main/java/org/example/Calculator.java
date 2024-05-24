package org.example;

import static java.lang.System.exit;

public class Calculator {
    Input input;
    Interpreter interpreter;

    public Calculator(Input input, Interpreter interpreter) {
        this.input = input;
        this.interpreter = interpreter;
    }

    public void performCalculationv2() {
        String prompt = input.getInput();
        try {
            int result = interpreter.interpretv3(prompt);
            System.out.println("The result of " + prompt + " = " + result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    public void performv3() {
        String prompt = input.getInput();
        try {
            int result = interpreter.interpretv3(prompt);
            System.out.println("The result:" + result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }
}
