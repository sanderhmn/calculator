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
            int result = interpreter.interpret(prompt);
            System.out.print("The result of " + prompt + " = " + result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            exit(1);
        }
    }
}
