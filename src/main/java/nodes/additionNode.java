package nodes;

public class additionNode implements operatorNode {
    final Node leftChild;
    Node rightChild;

    public additionNode(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public additionNode(String leftVal, String rightVal) {
        this(new NumberNode(leftVal), new NumberNode(rightVal));
    }

    public additionNode(Node leftChild, String rightVal) {
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
        return leftChild.getValue() + rightChild.getValue();
    }
}
