package expression;

import parser.Token;

public abstract class TokenRepresentation {
    protected final Token token;

    public TokenRepresentation(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
