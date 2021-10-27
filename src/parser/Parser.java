package parser;

import exception.ParsingException;
import expression.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Token> getTokens(
            String expression) throws ParsingException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<Token> res = new ArrayList<>();
        while (true) {
            TokenEnum tokenEnum = tokenizer.getNextToken();
            if (tokenEnum == TokenEnum.END) {
                break;
            }
            Token curToken;
            if (tokenEnum == TokenEnum.NUMBER) {
                int value = tokenizer.getValue();
                curToken = tokenEnum.getTokenRepresentationClass().getConstructor(Integer.TYPE).newInstance(value);
            } else {
                curToken = tokenEnum.getTokenRepresentationClass().getConstructor().newInstance();
            }
            res.add(curToken);
        }
        return res;
    }

}
