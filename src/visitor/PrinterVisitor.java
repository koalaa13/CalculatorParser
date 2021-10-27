package visitor;

import expression.TokenRepresentation;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PrinterVisitor extends AbstractVisitor {
    private final OutputStream outputStream;

    public PrinterVisitor(OutputStream outputStream, List<TokenRepresentation> tokenRepresentations) {
        super(tokenRepresentations);
        this.outputStream = outputStream;
    }

    public void printTokens() throws IOException {
        for (TokenRepresentation tokenRepresentation : tokenRepresentations) {
            outputStream.write(tokenRepresentation.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.write(' ');
        }
    }
}
