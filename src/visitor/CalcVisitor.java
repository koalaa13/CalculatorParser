package visitor;

import exception.DivisionByZeroException;
import exception.OverflowException;
import expression.Bracket;
import expression.Number;
import expression.Operation;
import parser.TokenEnum;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final Stack<Integer> stack;

    public CalcVisitor() {
        this.stack = new Stack<>();
    }

    @Override
    public void visit(Number token) {
        int number = token.getValue();
        stack.push(number);
    }

    @Override
    public void visit(Bracket token) {
        // do nothing
    }

    @Override
    public void visit(Operation token) throws OverflowException, DivisionByZeroException {
        TokenEnum tokenEnum = token.getTokenEnum();
        int x = stack.pop();
        int y = stack.pop();
        if (tokenEnum == TokenEnum.ADD) {
            Helper.checkAdd(x, y);
            stack.push(x + y);
        }
        if (tokenEnum == TokenEnum.SUB) {
            Helper.checkSubtract(x, y);
            stack.push(x - y);
        }
        if (tokenEnum == TokenEnum.DIV) {
            Helper.checkDivide(x, y);
            stack.push(x / y);
        }
        if (tokenEnum == TokenEnum.MUL) {
            Helper.checkMultiply(x, y);
            stack.push(x * y);
        }
    }

    public int getResult() {
        return stack.peek();
    }
}
