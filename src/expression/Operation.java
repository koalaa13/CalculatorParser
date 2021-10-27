package expression;

import parser.Token;

public abstract class Operation extends TokenRepresentation {
    private final int priority;

    public int getPriority() {
        return priority;
    }

    public Operation(int priority, Token token) {
        super(token);
        this.priority = priority;
    }
}
