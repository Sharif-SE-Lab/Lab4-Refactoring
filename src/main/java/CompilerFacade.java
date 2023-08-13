import Utils.IOUtils;
import codeGenerator.CodeGenerator;
import errorHandler.ErrorHandler;
import javafx.util.Pair;
import parser.Parser;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

import java.util.LinkedList;
import java.util.Scanner;

public class CompilerFacade {
    private final Scanner scanner;

    public CompilerFacade(Scanner scanner) {
        this.scanner = scanner;
    }

    public void compile() {
        Parser parser = new Parser();
        try {
            String code = IOUtils.getInstance().readWholeInput(scanner);
            LinkedList<Token> tokens = new lexicalAnalyzer(code).getTokens();
            LinkedList<Pair<Integer, Token>> abstractSyntaxTree = parser.generateAbstractSyntaxTree(tokens);
            String compiledCode = new CodeGenerator(abstractSyntaxTree).generateCode();
            if (ErrorHandler.hasError) {
                throw new Exception();
            } else {
                test(compiledCode, IOUtils.getInstance().readFile("src/main/resources/expectedOutput"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test(String calculatedOutput, String expectedOutput) {
        if (!calculatedOutput.equals(expectedOutput)) {
            System.out.println("Wrong Parse");
        } else {
            System.out.println("Successful Parse");
        }
    }
}
