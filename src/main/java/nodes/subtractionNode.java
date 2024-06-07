package nodes;

public class subtractionNode implements operatorNode {
    final Node leftChild;
    Node rightChild;

    public subtractionNode(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public subtractionNode(String leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    public subtractionNode(Node leftChild, String rightVal) {
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
        return leftChild.getValue() - rightChild.getValue();
    }
}
