package org.example;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Interpreter {
    public int interpret(String prompt) {
        ArrayList<String> splittedPrompt = new ArrayList<>(List.of(prompt.split("\\s+")));

        // Create HashMap of operators and corresponding operation
        HashMap<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();
        //operations.put("*", (x, y) -> x * y);
        //operations.put("/", (x, y) -> x / y);
        operations.put("+", (x, y) -> x + y);
        operations.put("-", (x, y) -> x - y);

        // Loop over operations in order of (PE)MDAS, perform them and simplify prompt for every operator
        for (String op : operations.keySet()) {
            for (int j = 0; j < splittedPrompt.size(); j++) {
                if (Objects.equals(splittedPrompt.get(j), op)) {

                    // Retrieve correct operation and operands
                    BiFunction<Integer, Integer, Integer> operation = operations.get(op);
                    Integer a = Integer.valueOf(splittedPrompt.get(j - 1));
                    Integer b = Integer.valueOf(splittedPrompt.get(j + 1));

                    String subResult = String.valueOf(operation.apply(a,b));

                    // Update splitted prompt according to subResult
                    splittedPrompt.set(j, subResult);
                    splittedPrompt.remove(j + 1); // first this one to not mess with indexation
                    splittedPrompt.remove(j - 1);

                    // Adjust index as not to skip an entry
                    j--;

                    // Done when single number left
                    if (splittedPrompt.size() == 1) {
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(splittedPrompt.get(0));
    }
}
