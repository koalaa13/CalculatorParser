package visitor;

import expression.Bracket;
import expression.Number;
import expression.Operation;
import expression.Token;
import parser.TokenEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This visitor transform tokens to reversed polish notation
 */
public class ParserVisitor implements TokenVisitor {
    private final Stack<Token> stack;
    private final List<Token> res;

    public ParserVisitor() {
        this.stack = new Stack<>();
        this.res = new ArrayList<>();
    }

    public List<Token> getReversedPolishNotation() {
        while (!stack.isEmpty()) {
            res.add(stack.peek());
            stack.pop();
        }
        return res;
    }

    @Override
    public void visit(Number token) {
        res.add(token);
    }

    @Override
    public void visit(Bracket token) {
        TokenEnum tokenEnum = token.getTokenEnum();
        if (tokenEnum == TokenEnum.OPEN_BRACKET) {
            stack.push(token);
        }
        if (tokenEnum == TokenEnum.CLOSE_BRACKET) {
            while (!stack.isEmpty() && stack.peek().getTokenEnum() != TokenEnum.OPEN_BRACKET) {
                res.add(stack.peek());
                stack.pop();
            }
            stack.pop();
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            if (!(stack.peek() instanceof Operation)) {
                break;
            }
            Operation peekOperation = (Operation) stack.peek();
            if (peekOperation.getPriority() > token.getPriority()) {
                res.add(stack.peek());
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }
}
