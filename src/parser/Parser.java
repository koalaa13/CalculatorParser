package parser;

import exception.ParsingException;
import expression.Begin;
import expression.TokenRepresentation;
import parser.Token;
import parser.Tokenizer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<TokenRepresentation> getTokens(
            String expression) throws ParsingException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<TokenRepresentation> res = new ArrayList<>();
        res.add(new Begin());
        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.getNextToken();
            TokenRepresentation curTokenRepresentation;
            if (token == Token.NUMBER) {
                int value = tokenizer.getValue();
                curTokenRepresentation = token.getTokenRepresentationClass().getConstructor(Integer.TYPE).newInstance(value);
            } else {
                curTokenRepresentation = token.getTokenRepresentationClass().getConstructor().newInstance();
            }
            res.add(curTokenRepresentation);
        }
        return res;
    }

}
