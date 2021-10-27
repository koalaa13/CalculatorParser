package visitor;

import expression.Number;
import expression.Operation;
import expression.TokenRepresentation;
import parser.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This visitor transform tokens to reversed polish notation
 */
public class ParserVisitor extends AbstractVisitor {
    public ParserVisitor(List<TokenRepresentation> tokenRepresentations) {
        super(tokenRepresentations);
    }

    public List<TokenRepresentation> getReversedPolishNotation() {
        Stack<TokenRepresentation> stack = new Stack<>();
        List<TokenRepresentation> res = new ArrayList<>();
        for (TokenRepresentation tokenRepresentation : tokenRepresentations) {
            Token token = tokenRepresentation.getToken();
            if (token == Token.BEGIN || token == Token.END) {
                continue;
            }
            if (token == Token.NUMBER) {
                res.add(tokenRepresentation);
                continue;
            }
            if (token == Token.OPEN_BRACKET) {
                stack.push(tokenRepresentation);
                continue;
            }
            if (token == Token.CLOSE_BRACKET) {
                while (!stack.isEmpty() && stack.peek().getToken() != Token.OPEN_BRACKET) {
                    res.add(stack.peek());
                    stack.pop();
                }
                stack.pop();
                continue;
            }
            Operation operation = (Operation) tokenRepresentation;
            while (!stack.isEmpty()) {
                if (!(stack.peek() instanceof Operation)) {
                    break;
                }
                Operation peekOperation = (Operation) stack.peek();
                if (peekOperation.getPriority() > operation.getPriority()) {
                    res.add(stack.peek());
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(tokenRepresentation);
        }
        while (!stack.isEmpty()) {
            res.add(stack.peek());
            stack.pop();
        }
        return res;
    }
}
