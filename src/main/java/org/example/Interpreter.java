package org.example;

import java.util.*;
import java.util.function.BiFunction;

import static java.lang.System.exit;

public class Interpreter {
    public ArrayList<String> tokenizePromptToArray(String prompt) throws Exception {
        ArrayList<String> splitPrompt = new ArrayList<>(List.of(prompt.split("\\s+")));

        boolean expectNumber = true; // start with number expected
        String numberRegex = "-?\\d+";
        String operatorRegex = "[+\\-*/]";

        String expectedRegex;

        for (String entry : splitPrompt) {
            if (expectNumber) {
                expectedRegex = numberRegex; // start with number expected
                if (!entry.matches(expectedRegex)) {
                    throw new Exception("Incorrect format: please enter whitespaces between numbers and operators");
                }
            } else {
                expectedRegex = operatorRegex;
                if (!entry.matches(expectedRegex)) {
                    throw new Exception("Incorrect format: please enter whitespaces between numbers and operators");
                }
            }

            expectNumber = !expectNumber;
        }
        return splitPrompt;
    }
    
    public int interpret(String prompt) {
        ArrayList<String> splitPrompt = new ArrayList<>();
        try {
            splitPrompt = this.tokenizePromptToArray(prompt);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            exit(2);
        }

        // Create HashMap of operators and corresponding operation
        HashMap<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();
        operations.put("*", (x, y) -> x * y);
        operations.put("/", (x, y) -> x / y);
        operations.put("+", (x, y) -> x + y);
        operations.put("-", (x, y) -> x - y);

        // Loop over operations in order of (PE)MDAS, perform them and simplify prompt for every operator
        for (String op : operations.keySet()) {
            for (int j = 0; j < splitPrompt.size(); j++) {
                if (Objects.equals(splitPrompt.get(j), op)) {

                    // Retrieve correct operation and operands
                    BiFunction<Integer, Integer, Integer> operation = operations.get(op);
                    Integer a = Integer.valueOf(splitPrompt.get(j - 1));
                    Integer b = Integer.valueOf(splitPrompt.get(j + 1));

                    String subResult = String.valueOf(operation.apply(a,b));

                    // Update splitted prompt according to subResult
                    splitPrompt.set(j, subResult);
                    splitPrompt.remove(j + 1); // first this one to not mess with indexation
                    splitPrompt.remove(j - 1);

                    // Adjust index as not to skip an entry
                    j--;

                    // Done when single number left
                    if (splitPrompt.size() == 1) {
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(splitPrompt.getFirst());
    }
}
