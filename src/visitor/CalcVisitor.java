package visitor;

import exception.DivisionByZeroException;
import exception.OverflowException;
import expression.Number;
import expression.Operation;
import expression.TokenRepresentation;
import parser.Token;

import java.util.List;
import java.util.Stack;

public class CalcVisitor extends AbstractVisitor {
    public CalcVisitor(List<TokenRepresentation> tokenRepresentations) {
        super(tokenRepresentations);
    }

    public int calculate() throws OverflowException, DivisionByZeroException {
        Stack<Integer> stack = new Stack<>();
        for (TokenRepresentation tokenRepresentation : tokenRepresentations) {
            if (tokenRepresentation instanceof Number) {
                int number = ((Number) tokenRepresentation).getValue();
                stack.push(number);
            } else {
                Token token = tokenRepresentation.getToken();
                int x = stack.pop();
                int y = stack.pop();
                if (token == Token.ADD) {
                    Helper.checkAdd(x, y);
                    stack.push(x + y);
                }
                if (token == Token.SUB) {
                    Helper.checkSubtract(x, y);
                    stack.push(x - y);
                }
                if (token == Token.DIV) {
                    Helper.checkDivide(x, y);
                    stack.push(x / y);
                }
                if (token == Token.MUL) {
                    Helper.checkMultiply(x, y);
                    stack.push(x * y);
                }
            }
        }
        return stack.peek();
    }
}
