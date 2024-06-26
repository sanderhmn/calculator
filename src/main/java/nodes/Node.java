package nodes;

public interface Node {
    public double getValue();
    Node getRightChild();
    int getPriority();
    void setRightChild(Node rightChild);
}
