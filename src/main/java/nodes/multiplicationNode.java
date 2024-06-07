package nodes;

public class multiplicationNode implements operatorNode {
    final Node leftChild;
    Node rightChild;

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

    @Override
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    @Override
    public double getValue() {
        return leftChild.getValue() * rightChild.getValue();
    }
}
