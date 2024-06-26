package org.example;

import nodes.*;
import java.util.*;
import static java.lang.System.exit;

public class Interpreter {
    public static final int MULTIPLICATIONPRIO = 2;
    public static final int DIVISIONPRIO = 2;
    public static final int ADDITIONPRIO = 1;
    public static final int SUBTRACTIONPRIO = 1;

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

        // FIXME all the switch expressions can be replaced with a Factory/Builder but idk which one
        Node firstCalcNode = switch (splitPrompt.get(1)) {
            case "*" -> new multiplicationNode(splitPrompt.getFirst(), splitPrompt.get(2));
            case "/" -> new divisionNode(splitPrompt.getFirst(), splitPrompt.get(2));
            case "+" -> new additionNode(splitPrompt.getFirst(), splitPrompt.get(2));
            case "-" -> new subtractionNode(splitPrompt.getFirst(), splitPrompt.get(2));
            default -> new NumberNode(-999);
        };

        Node currentRootNode = firstCalcNode;

        for (int i = 3; i < splitPrompt.size(); i += 2) {
            String operator = splitPrompt.get(i);
            String secondOperand = splitPrompt.get(i + 1);

            int operatorPrio = switch (operator) {
                case "*" -> MULTIPLICATIONPRIO;
                case "/" -> DIVISIONPRIO;
                case "+" -> ADDITIONPRIO;
                case "-" -> SUBTRACTIONPRIO;
                default -> -1;
            };

            // if current operation priority < priority of operation of currentRootNode
            // replace rootNode with new operator node
            if (operatorPrio < currentRootNode.getPriority()) {
                currentRootNode = switch (operator) {
                    case "*" -> new multiplicationNode(currentRootNode, new NumberNode(secondOperand));
                    case "/" -> new divisionNode(currentRootNode, new NumberNode(secondOperand));
                    case "+" -> new additionNode(currentRootNode, new NumberNode(secondOperand));
                    case "-" -> new subtractionNode(currentRootNode, new NumberNode(secondOperand));
                    default -> currentRootNode;
                };
            }

            // elif current operation priority > priority of operation of currentRootNode
            else {
                Node tempNode = currentRootNode;

                // Find dangling leaf and save value to add to new node
                // FIXME while !operatorPrio > tempNode.getRightChild().getPriority()

                // FIXME better? (while operatrorPrio !> tempNOde.getPrio())
                while (!((tempNode.getRightChild()) instanceof NumberNode rightLeaf)) { //gives error because tM=cRN=numberNode has no gRC()
                    tempNode = tempNode.getRightChild();
                }
                double tempLeftVal = rightLeaf.getValue();

                // Build new node and replace old leaf with it
                Node tempNewChild = switch (operator) {
                    case "*" -> new multiplicationNode(tempLeftVal, secondOperand);
                    case "/" -> new divisionNode(tempLeftVal, secondOperand);
                    case "+" -> new additionNode(tempLeftVal, secondOperand);
                    case "-" -> new subtractionNode(tempLeftVal, secondOperand);
                    default -> currentRootNode;
                };
                tempNode.setRightChild(tempNewChild);

            }

            // FIXME hardcoded for 2 priorities of operation, should look for lower priority operation ()^
            // FIXME how to solve compiler not accepting numberNode?
            // maybe by casting cRootNode to operatorNode after first loop
            // or make seperate primaryNumberNode and operatorNode currentRootNode
        }
        return currentRootNode;
    }
}
