package org.example;

public class Interpreter {

    public int interpret(String prompt) {
        String[] parsed = prompt.split("\\+");

        int sum = 0;
        for (String a : parsed) {

            sum += Integer.valueOf(a.trim());
        }

        return sum;
//        for (int i = 0; i < input.length(); i++) {
//            System.out.println(input.charAt(i));
//
//            //Character.isDigit()
//        }
    }
}
