package org.example;

public class numberNode implements Node {
    private final double value;
    private Node leftChild;
    private Node rightChild ;

    public numberNode(double value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    @Override
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
