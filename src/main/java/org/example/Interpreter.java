package org.example;

import java.util.Arrays;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Interpreter {

    public int interpret(String prompt) {
        String[] parsed = prompt.split("\\+");
        parsed = Arrays.stream(parsed)
                .map(String::trim)
                .toArray(String[]::new);

        int sum = 0;
        for (String a : parsed) {
            if (Pattern.matches("^[0-9-]+$", a)) {
                sum += Integer.valueOf(a.trim());
            } else {
                throw new RuntimeException("Invalid Input: alphabetic values");
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
