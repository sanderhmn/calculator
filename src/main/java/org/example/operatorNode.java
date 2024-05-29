package org.example;

public class operatorNode implements Node {
    private final String value;
    private Node leftChild;
    private Node rightChild;

    public operatorNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
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
