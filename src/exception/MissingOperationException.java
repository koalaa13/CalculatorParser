package exception;

public class MissingOperationException extends ParsingException {
    public MissingOperationException(String expression, int ind) {
        super("Missing operation in expression", expression, ind);
    }
}
