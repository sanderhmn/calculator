package org.example;

import nodes.*;

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
    
    public double buildTree(String prompt) {
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

        // 5 + 3 * 5 * 10 - 5
        // Map<String, Node> = splitPrompt with bool to indicate if node has been made

        // 2 + 3 * 2
        //   /\
        //  2  *
        //     /\
        //    3  2


        // 2 * 2 - 3 * 3
        //
        //    *
        //   /\
        //  2  2

        //       -
        //       /\
        //      *  3
        //     /\
        //    2  2

        //        -
        //       / \
        //      *   -
        //     /\   /\
        //    2  2 3  -
        //            /\
        //           3  1

        // 2 * 2 - 3 - 3 - 1 * 1

        //        -
        //       / \
        //      *   -
        //     /\   /\
        //    2  2 3  -
        //            /\
        //           3  *
        //              /\
        //             1  1

        // if operation priority is less than current
        //      new parent node
        // else
        //       edit child node of current node w/ new operator node


        // 2 * 2 - 3 * 3

        Node currentRootNode = new NumberNode(splitPrompt.getFirst());

        for (int i = 1; i < splitPrompt.size(); i+=2) {
            String operator = splitPrompt.get(i);
            String secondOperand = splitPrompt.get(i + 1);
            // if currrent operation priotiry < operation of currentRootNode
                // replace rootNode with new operator node

            // if current operation priority < prio of operation of currentRootNode
            currentRootNode = switch (operator) {
                case "*" -> new multiplicationNode(currentRootNode, new NumberNode(secondOperand));
                case "/" -> new divisionNode(currentRootNode, new NumberNode(secondOperand));
                case "+" -> new additionNode(currentRootNode, new NumberNode(secondOperand));
                case "-" -> new subtractionNode(currentRootNode, new NumberNode(secondOperand));
                default -> currentRootNode;
            };

            //currentRootNode = new multiplicationNode(currentRootNode, new numberNode(secondOperand));

            //currentRootNode = new subtractionNode(currentRootNode, new numberNode(secondOperand));

            // current operation priority > prio of op of cRN
//            Node tempLeftVal = currentRootNode.getRightChild().getValue();
//            Node tempNewChild = new multiplicationNode(tempLeftVal, secondOperand);
//            currentRootNode.setRightChild(tempNewChild);

            // tempLEftVal
            // while rightChild != leaf/numberNode
            //      getRightChild
            // return rightChild.getValue()

            // tempNewChild
            // switch depending on operator
            // new opNode(tempLeftVal, secondOperand)

            // FIXME hardcoded for 2 priorities of operation, should look for lower priotiy operation ()^
            // FIXME how to solve compiler not accepting numberNode?
            // maybe by casting cRootNode to operatorNode after first loop
            // or make seperate primaryNumberNode and operatorNode currentRootNode

            Node tempNode = currentRootNode;
            while (!((tempNode.getRightChild()) instanceof NumberNode)) {
                tempNode = tempNode.getRightChild();
            }
            double tempLeftVal = tempNode.getRightChild().getValue();

            Node tempNewChild = switch (operator) {
                case "*" -> new multiplicationNode(tempLeftVal, secondOperand);
                case "/" -> new divisionNode(tempLeftVal, secondOperand);
                case "+" -> new additionNode(tempLeftVal, secondOperand);
                case "-" -> new subtractionNode(tempLeftVal, secondOperand);
                default -> currentRootNode;
            };






        }


        // Loop over operations in order of (PE)MDAS, perform them and simplify prompt for every operator
        for (String op : operations.keySet()) {
            for (int j = 0; j < splitPrompt.size(); j++) {
                if (Objects.equals(splitPrompt.get(j), op)) {
                    Node a = new NumberNode(splitPrompt.get(j - 1));
                    Node b = new NumberNode(splitPrompt.get(j + 1));

                    if (Objects.equals(op, "*")) {
                        operatorNode node = new multiplicationNode(a, b);
                    } else if (Objects.equals(op, "/")) {
                        operatorNode node = new divisionNode(a, b);
                    } else if (Objects.equals(op, "+")) {
                        operatorNode node = new additionNode(a, b);
                    } else if (Objects.equals(op, "-")) {
                        operatorNode node = new subtractionNode(a, b);
                    }
                }
            }
        }
        return Double.parseDouble(splitPrompt.getFirst());
    }
}
