package expression;

import parser.TokenEnum;
import visitor.TokenVisitor;

public class Number extends Token {
    private final int value;

    public Number(int value) {
        super(TokenEnum.NUMBER);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(TokenVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return tokenEnum.toString() + '(' + value + ')';
    }
}
