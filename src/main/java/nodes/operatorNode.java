package nodes;

public interface operatorNode extends Node {
    Node getLeftChild();
    Node getRightChild();
    void setRightChild(Node rightChild);

}
