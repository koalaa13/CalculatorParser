package parser;

import expression.Number;
import expression.*;

public enum TokenEnum {
    ADD(Add.class),
    SUB(Sub.class),
    MUL(Mul.class),
    DIV(Div.class),
    OPEN_BRACKET(OpenBracket.class),
    CLOSE_BRACKET(CloseBracket.class),
    NUMBER(Number.class),
    BEGIN(null),
    END(null);

    private final Class<? extends Token> tokenRepresentationClass;

    TokenEnum(Class<? extends Token> tokenRepresentationClass) {
        this.tokenRepresentationClass = tokenRepresentationClass;
    }

    public Class<? extends Token> getTokenRepresentationClass() {
        return tokenRepresentationClass;
    }
}
