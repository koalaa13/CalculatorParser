package visitor;

import expression.Bracket;
import expression.Number;
import expression.Operation;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PrinterVisitor implements TokenVisitor {
    private final OutputStream outputStream;

    public PrinterVisitor(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void visit(Number token) throws Exception {
        outputStream.write(token.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.write(' ');
    }

    @Override
    public void visit(Bracket token) throws Exception {
        outputStream.write(token.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.write(' ');
    }

    @Override
    public void visit(Operation token) throws Exception {
        outputStream.write(token.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.write(' ');
    }
}
