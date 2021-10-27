package parser;

import exception.*;

import java.util.HashSet;

public class Tokenizer {
    private final String expression;
    private int ind, value, balance;
    private Token curToken;
    private static final HashSet<Token> binaryOperations = new HashSet<>();

    public Tokenizer(String newExpression) {
        expression = newExpression;
        ind = balance = 0;
        curToken = Token.BEGIN;
    }

    static {
        binaryOperations.add(Token.ADD);
        binaryOperations.add(Token.SUB);
        binaryOperations.add(Token.MUL);
        binaryOperations.add(Token.DIV);
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

    public Token getCurToken() {
        return curToken;
    }

    public Token getNextToken() throws ParsingException {
        nextToken();
        return curToken;
    }

    public boolean hasNextToken() {
        return curToken != Token.END;
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
        if (curToken == Token.BEGIN || curToken == Token.OPEN_BRACKET || binaryOperations.contains(curToken)) {
            throw new MissingOperandException(expression, ind);
        }
    }

    private void checkForOperation() throws MissingOperationException {
        if (curToken == Token.CLOSE_BRACKET || curToken == Token.NUMBER) {
            throw new MissingOperationException(expression, ind);
        }
    }

    private void nextToken() throws ParsingException {
        skipWhiteSpaces();
        if (ind >= expression.length()) {
            checkForOperand();
            curToken = Token.END;
            return;
        }
        char c = expression.charAt(ind);
        switch (c) {
            case '+':
                checkForOperand();
                curToken = Token.ADD;
                break;
            case '*':
                checkForOperand();
                curToken = Token.MUL;
                break;
            case '/':
                checkForOperand();
                curToken = Token.DIV;
                break;
            case '-':
                if (curToken == Token.NUMBER || curToken == Token.CLOSE_BRACKET) {
                    curToken = Token.SUB;
                } else {
                    if (ind + 1 >= expression.length()) {
                        throw new MissingOperandException(expression, ind);
                    } else {
                        if (isPartOfNumber(expression.charAt(ind + 1))) {
                            ind++;
                            value = getInt("-" + getNumber());
                            curToken = Token.NUMBER;
                        } else {
                            curToken = Token.SUB;
                        }
                    }
                }
                break;
            case '(':
                if (curToken == Token.CLOSE_BRACKET || curToken == Token.NUMBER) {
                    throw new MissingOperationException(expression, ind);
                }
                balance++;
                curToken = Token.OPEN_BRACKET;
                break;
            case ')':
                if (binaryOperations.contains(curToken) || curToken == Token.OPEN_BRACKET) {
                    throw new MissingOperandException(expression, ind);
                }
                if (balance == 0) {
                    throw new UnpairedBracketsException("There is unpaired close bracket in a expression", expression, ind);
                }
                balance--;
                curToken = Token.CLOSE_BRACKET;
                break;
            default:
                if (Character.isDigit(c)) {
                    checkForOperation();
                    value = getInt(getNumber());
                    curToken = Token.NUMBER;
                } else {
                    throw new ParsingException("Parsing error happened", expression, ind);
                }
        }
        ++ind;
    }
}
