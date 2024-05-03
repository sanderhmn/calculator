package org.example;

public class AdditionResult implements ResultIF{
    String prompt = null;
    Operation operation = null;
    int result = -999;

    public void decodePrompt(String prompt) {
        // To be completed
//        if (prompt.contains("+")) {
//            String[] numbers = prompt.split("\\+");
//            int a = Integer.parseInt(numbers[0].trim());
//            int b = Integer.parseInt(numbers[1].trim());
//        } else {
//            System.out.println("No addition?");
//        };
    }

    public void printResult() {
        // To be implemented
    };
}
