package org.example;

public interface Node {
    Object getValue();
    Node getLeftChild();
    Node getRightChild();
    void setLeftChild(Node leftChild);
    void setRightChild(Node leftChild);
}
