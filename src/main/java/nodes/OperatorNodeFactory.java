package nodes;

public class OperatorNodeFactory {
    // Node/Node construction
    public Node buildNode(String operator, Node leftChild, Node rightChild) {
        return switch (operator) {
            case "*" -> new multiplicationNode(leftChild, rightChild);
            case "/" -> new divisionNode(leftChild, rightChild);
            case "+" -> new additionNode(leftChild, rightChild);
            case "-" -> new subtractionNode(leftChild, rightChild);
            default -> new NumberNode(-999);
        };
    }

    public Node buildNode(String operator, String leftVal, String rightVal) {
        return switch (operator) {
            case "*" -> new multiplicationNode(leftVal, rightVal);
            case "/" -> new divisionNode(leftVal, rightVal);
            case "+" -> new additionNode(leftVal, rightVal);
            case "-" -> new subtractionNode(leftVal, rightVal);
            default -> new NumberNode(-999);
        };
    }
}
