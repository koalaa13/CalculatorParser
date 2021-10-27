import exception.DivisionByZeroException;
import exception.OverflowException;
import exception.ParsingException;
import expression.TokenRepresentation;
import parser.Parser;
import visitor.AbstractVisitor;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrinterVisitor;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(
            String[] args) throws ParsingException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, DivisionByZeroException, OverflowException {
        System.out.println("Enter a expression in one line");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        Parser parser = new Parser();

        System.out.println("Parsed tokens in infix form");
        List<TokenRepresentation> infixTokenRepresentations = parser.getTokens(expression);
        infixTokenRepresentations.forEach(token -> System.out.print(token.toString() + ' '));
        System.out.println();
        System.out.println();

        ParserVisitor parserVisitor = new ParserVisitor(infixTokenRepresentations);
        System.out.println("Parsed tokens in reversed polish notation");
        List<TokenRepresentation> RPNTokenRepresentations = parserVisitor.getReversedPolishNotation();

        PrinterVisitor printerVisitor = new PrinterVisitor(System.out, RPNTokenRepresentations);
        printerVisitor.printTokens();
        System.out.println();
        System.out.println();

        CalcVisitor calcVisitor = new CalcVisitor(RPNTokenRepresentations);
        System.out.println("Calculation result");
        System.out.println(calcVisitor.calculate());
    }
}
