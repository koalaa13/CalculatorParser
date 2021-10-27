package expression;

import parser.TokenEnum;
import visitor.TokenVisitor;

public abstract class Operation extends Token {
    private final int priority;

    public int getPriority() {
        return priority;
    }

    public Operation(int priority, TokenEnum tokenEnum) {
        super(tokenEnum);
        this.priority = priority;
    }

    @Override
    public void accept(TokenVisitor visitor) throws Exception {
        visitor.visit(this);
    }
}
