package nodes;

import static org.example.Interpreter.MULTIPLICATIONPRIO;

public class multiplicationNode implements operatorNode {
    final Node leftChild;
    Node rightChild;
    int priority = MULTIPLICATIONPRIO;

    public multiplicationNode(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public multiplicationNode(String leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    public multiplicationNode(Node leftChild, String rightVal) {
        this(leftChild, new NumberNode(rightVal));
    }

    public multiplicationNode(double leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    // FIXME feels ugly to have 4 different constructors

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
        return leftChild.getValue() * rightChild.getValue();
    }


}
