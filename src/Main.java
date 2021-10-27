import expression.Token;
import parser.Parser;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrinterVisitor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(
            String[] args) throws Exception {
        System.out.println("Enter a expression in one line");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        Parser parser = new Parser();

        System.out.println("Parsed tokens in infix form");
        List<Token> infixTokens = parser.getTokens(expression);
        infixTokens.forEach(token -> System.out.print(token.toString() + ' '));
        System.out.println();
        System.out.println();


        ParserVisitor parserVisitor = new ParserVisitor();
        for (Token token : infixTokens) {
            token.accept(parserVisitor);
        }
        System.out.println("Parsed tokens in reversed polish notation");
        List<Token> RPNTokens = parserVisitor.getReversedPolishNotation();

        PrinterVisitor printerVisitor = new PrinterVisitor(System.out);
        for (Token token : RPNTokens) {
            token.accept(printerVisitor);
        }
        System.out.println();
        System.out.println();

        CalcVisitor calcVisitor = new CalcVisitor();
        for (Token token : RPNTokens) {
            token.accept(calcVisitor);
        }
        System.out.println("Calculation result");
        System.out.println(calcVisitor.getResult());
    }
}
