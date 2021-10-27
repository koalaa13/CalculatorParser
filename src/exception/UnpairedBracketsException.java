package exception;

public class UnpairedBracketsException extends ParsingException {
    public UnpairedBracketsException(String s, String expression, int ind) {
        super(s, expression, ind);
    }
}
