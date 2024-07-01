package nodes;

import static java.lang.System.exit;
import static org.example.Interpreter.DIVISIONPRIO;

public class divisionNode implements operatorNode {
    final Node leftChild;
    Node rightChild;
    int priority = DIVISIONPRIO;

    public divisionNode(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public divisionNode(String leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    public divisionNode(Node leftChild, String rightVal) {
        this(leftChild, new NumberNode(rightVal));
    }

    public divisionNode(double leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    @Override
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public double getValue() {
        if (rightChild.getValue() == 0) {
            exit(4);
        }
        return (leftChild.getValue() / rightChild.getValue());
    }

    @Override
    public String toString() {
        return "[%s / %s]".formatted(leftChild, rightChild);
    }
}
