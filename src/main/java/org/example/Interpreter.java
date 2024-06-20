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
    
    public Node buildTree(String prompt) {
        // FIXME buildTree re-throws excep -> no try/catch method -> foutafhandeling in calc class
        ArrayList<String> splitPrompt = new ArrayList<>();
        try {
            splitPrompt = this.tokenizePromptToArray(prompt);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            exit(2);
        }

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

        for (int i = 1; i < splitPrompt.size(); i += 2) {
            String operator = splitPrompt.get(i);
            String secondOperand = splitPrompt.get(i + 1);

            // if current operation priority < operation of currentRootNode
                // replace rootNode with new operator node

            // if current operation priority < priority of operation of currentRootNode
            currentRootNode = switch (operator) {
                case "*" -> new multiplicationNode(currentRootNode, new NumberNode(secondOperand));
                case "/" -> new divisionNode(currentRootNode, new NumberNode(secondOperand));
                case "+" -> new additionNode(currentRootNode, new NumberNode(secondOperand));
                case "-" -> new subtractionNode(currentRootNode, new NumberNode(secondOperand));
                default -> currentRootNode;
            };

            // elif current operation priority > priority of operation of currentRootNode
            Node tempNode = currentRootNode;
            while (!((tempNode.getRightChild()) instanceof NumberNode rightLeaf)) {
                tempNode = tempNode.getRightChild();
            }

            double tempLeftVal = rightLeaf.getValue();

            Node tempNewChild = switch (operator) {
                case "*" -> new multiplicationNode(tempLeftVal, secondOperand);
                case "/" -> new divisionNode(tempLeftVal, secondOperand);
                case "+" -> new additionNode(tempLeftVal, secondOperand);
                case "-" -> new subtractionNode(tempLeftVal, secondOperand);
                default -> currentRootNode;
            };


            // FIXME hardcoded for 2 priorities of operation, should look for lower priority operation ()^
            // FIXME how to solve compiler not accepting numberNode?
            // maybe by casting cRootNode to operatorNode after first loop
            // or make seperate primaryNumberNode and operatorNode currentRootNode
        }
        return currentRootNode;
    }
}
