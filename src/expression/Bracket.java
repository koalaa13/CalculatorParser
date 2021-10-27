package expression;

import parser.TokenEnum;
import visitor.TokenVisitor;

public abstract class Bracket extends Token {
    protected Bracket(TokenEnum tokenEnum) {
        super(tokenEnum);
    }

    @Override
    public void accept(TokenVisitor visitor) throws Exception {
        visitor.visit(this);
    }
}
