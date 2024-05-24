package org.example;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Interpreter {
    public int interpretv3(String prompt) {
        ArrayList<String> splittedPrompt = new ArrayList<>(List.of(prompt.split("\\s+")));

        HashMap<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>();
        //operations.put("*", (x, y) -> x * y);
        //operations.put("/", (x, y) -> x / y);
        operations.put("+", (x, y) -> x + y);
        operations.put("-", (x, y) -> x - y);

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
                        System.out.println(splittedPrompt);
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(splittedPrompt.get(0));
    }
    public int interpretv2(String prompt) {
        String[] parsed = prompt.split("\\+");
        parsed = Arrays.stream(parsed)
                .map(String::trim)
                .toArray(String[]::new);

        int sum = 0;
        for (String a : parsed) {
            if (Pattern.matches("^[0-9-]+$", a)) {
                sum += Integer.valueOf(a.trim());
            } else {
                throw new RuntimeException("Invalid Input: non-numeric values");
            }

        }

        return sum;
//        for (int i = 0; i < input.length(); i++) {
//            System.out.println(input.charAt(i));
//
//            //Character.isDigit()
//        }
    }
}
