package exception;

public class MissingOperandException extends ParsingException {
    public MissingOperandException(String expression, int ind) {
        super("Missing operand in expression", expression, ind);
    }
}
