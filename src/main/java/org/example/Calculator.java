package org.example;

import nodes.Node;

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
            Node tree = interpreter.buildTree(prompt);
            double result = tree.getValue();

            System.out.println("The result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
