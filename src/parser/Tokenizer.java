package parser;

import exception.*;

import java.util.HashSet;

public class Tokenizer {
    private final String expression;
    private int ind, value, balance;
    private TokenEnum curTokenEnum;
    private static final HashSet<TokenEnum> binaryOperations = new HashSet<>();

    public Tokenizer(String newExpression) {
        expression = newExpression;
        ind = balance = 0;
        curTokenEnum = TokenEnum.BEGIN;
    }

    static {
        binaryOperations.add(TokenEnum.ADD);
        binaryOperations.add(TokenEnum.SUB);
        binaryOperations.add(TokenEnum.MUL);
        binaryOperations.add(TokenEnum.DIV);
    }

    public String getExpression() {
        return expression;
    }

    private int getInd() {
        return ind;
    }

    public int getValue() {
        return value;
    }

    public TokenEnum getCurToken() {
        return curTokenEnum;
    }

    public TokenEnum getNextToken() throws ParsingException {
        nextToken();
        return curTokenEnum;
    }

    private void skipWhiteSpaces() {
        while (ind < expression.length() && Character.isWhitespace(expression.charAt(ind))) {
            ++ind;
        }
    }

    private boolean isPartOfNumber(char c) {
        return Character.isDigit(c) || c == '.' || c == 'e';
    }

    private String getNumber() {
        int l = ind;
        while (ind < expression.length() && isPartOfNumber(expression.charAt(ind))) {
            ++ind;
        }
        int r = ind--;
        return expression.substring(l, r);
    }

    private int getInt(String s) throws IncorrectConstException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(e.getMessage(), expression, ind - s.length());
        }
    }

    private void checkForOperand() throws MissingOperandException {
        if (curTokenEnum == TokenEnum.BEGIN || curTokenEnum == TokenEnum.OPEN_BRACKET || binaryOperations.contains(curTokenEnum)) {
            throw new MissingOperandException(expression, ind);
        }
    }

    private void checkForOperation() throws MissingOperationException {
        if (curTokenEnum == TokenEnum.CLOSE_BRACKET || curTokenEnum == TokenEnum.NUMBER) {
            throw new MissingOperationException(expression, ind);
        }
    }

    private void nextToken() throws ParsingException {
        skipWhiteSpaces();
        if (ind >= expression.length()) {
            checkForOperand();
            curTokenEnum = TokenEnum.END;
            return;
        }
        char c = expression.charAt(ind);
        switch (c) {
            case '+':
                checkForOperand();
                curTokenEnum = TokenEnum.ADD;
                break;
            case '*':
                checkForOperand();
                curTokenEnum = TokenEnum.MUL;
                break;
            case '/':
                checkForOperand();
                curTokenEnum = TokenEnum.DIV;
                break;
            case '-':
                if (curTokenEnum == TokenEnum.NUMBER || curTokenEnum == TokenEnum.CLOSE_BRACKET) {
                    curTokenEnum = TokenEnum.SUB;
                } else {
                    if (ind + 1 >= expression.length()) {
                        throw new MissingOperandException(expression, ind);
                    } else {
                        if (isPartOfNumber(expression.charAt(ind + 1))) {
                            ind++;
                            value = getInt("-" + getNumber());
                            curTokenEnum = TokenEnum.NUMBER;
                        } else {
                            curTokenEnum = TokenEnum.SUB;
                        }
                    }
                }
                break;
            case '(':
                if (curTokenEnum == TokenEnum.CLOSE_BRACKET || curTokenEnum == TokenEnum.NUMBER) {
                    throw new MissingOperationException(expression, ind);
                }
                balance++;
                curTokenEnum = TokenEnum.OPEN_BRACKET;
                break;
            case ')':
                if (binaryOperations.contains(curTokenEnum) || curTokenEnum == TokenEnum.OPEN_BRACKET) {
                    throw new MissingOperandException(expression, ind);
                }
                if (balance == 0) {
                    throw new UnpairedBracketsException("There is unpaired close bracket in a expression", expression, ind);
                }
                balance--;
                curTokenEnum = TokenEnum.CLOSE_BRACKET;
                break;
            default:
                if (Character.isDigit(c)) {
                    checkForOperation();
                    value = getInt(getNumber());
                    curTokenEnum = TokenEnum.NUMBER;
                } else {
                    throw new ParsingException("Parsing error happened", expression, ind);
                }
        }
        ++ind;
    }
}
