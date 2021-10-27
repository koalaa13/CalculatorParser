package visitor;

import expression.Bracket;
import expression.Number;
import expression.Operation;

public interface TokenVisitor {
    void visit(Number token) throws Exception;

    void visit(Bracket token) throws Exception;

    void visit(Operation token) throws Exception;
}
