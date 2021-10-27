package expression;

import parser.Token;

public class Number extends TokenRepresentation {
    private final int value;

    public Number(int value) {
        super(Token.NUMBER);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return token.toString() + '(' + value + ')';
    }
}
