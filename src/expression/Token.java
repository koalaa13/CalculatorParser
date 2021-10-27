package expression;

import parser.TokenEnum;
import visitor.TokenVisitor;

public abstract class Token {
    protected final TokenEnum tokenEnum;

    protected Token(TokenEnum tokenEnum) {
        this.tokenEnum = tokenEnum;
    }

    public TokenEnum getTokenEnum() {
        return tokenEnum;
    }

    public abstract void accept(TokenVisitor visitor) throws Exception;

    @Override
    public String toString() {
        return tokenEnum.toString();
    }
}
