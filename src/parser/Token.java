package parser;

import expression.*;
import expression.Number;

public enum Token {
    ADD(Add.class),
    SUB(Sub.class),
    MUL(Mul.class),
    DIV(Div.class),
    OPEN_BRACKET(OpenBracket.class),
    CLOSE_BRACKET(CloseBracket.class),
    NUMBER(Number.class),
    BEGIN(Begin.class),
    END(End.class);

    private final Class<? extends TokenRepresentation> tokenRepresentationClass;

    Token(Class<? extends TokenRepresentation> tokenRepresentationClass) {
        this.tokenRepresentationClass = tokenRepresentationClass;
    }

    public Class<? extends TokenRepresentation> getTokenRepresentationClass() {
        return tokenRepresentationClass;
    }
}
