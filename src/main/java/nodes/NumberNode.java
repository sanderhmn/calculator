package nodes;

public class NumberNode implements Node{
    double value;

    public NumberNode(String val) {
        // Check whether input is actually a number
        // FIXME should I catch and re-throw an exception here? Can't I throw it up the stack at once?
        try {
            this.value = Double.parseDouble(val);
        } catch (Exception e) {
            throw e;
        }
    }

    public NumberNode(double val) {
        this.value = val;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public Node getRightChild() {
        return null;
    }

    @Override
    public int getPriority() {
        return -1; // set as -1 such that prio of operation is always higher
    }

    @Override
    public void setRightChild(Node rightChild) {
        // such empty, SOLID must be mad with me
    }

    @Override
    public String toString() {
        return "%s".formatted(value);
    }

}
