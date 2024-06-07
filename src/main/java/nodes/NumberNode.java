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
}
