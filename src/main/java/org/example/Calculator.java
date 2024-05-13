package org.example;

public class Calculator {
    Input input;
    Interpreter interpreter;

    public Calculator(Input input, Interpreter interpreter) {
        this.input = input;
        this.interpreter = interpreter;
    }

    public void performCalculation() {
        String prompt = input.getInput();
        int result = interpreter.interpret(prompt);
        System.out.print("The result of " + prompt + " = " + result);
    }

    public int add(int a, int b) {
        return (a + b);
    }

    public int subtract(int a, int b) {
        return (a - b);
    }
}
