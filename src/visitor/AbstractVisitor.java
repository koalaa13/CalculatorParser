package visitor;

import expression.TokenRepresentation;

import java.util.List;

public abstract class AbstractVisitor {
    protected final List<TokenRepresentation> tokenRepresentations;

    protected AbstractVisitor(List<TokenRepresentation> tokenRepresentations) {
        this.tokenRepresentations = tokenRepresentations;
    }
}
