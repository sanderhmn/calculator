public enum OperatorPrio {
    MULTIPLICATION(1), DIVISION(1), ADDITION(2), SUBTRACTION(2);
    private final int priority;

    private OperatorPrio(int priority) {
        this.priority = priority;
    }

    public boolean higherPriority(OperatorPrio other) {
        return (this.priority > other.priority);
    }
}
