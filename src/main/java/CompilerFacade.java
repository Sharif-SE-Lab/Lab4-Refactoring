import codeGenerator.CodeGenerator;
import javafx.util.Pair;
import parser.Parser;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class CompilerFacade {
    private final Scanner scanner;

    public CompilerFacade(Scanner scanner) {
        this.scanner = scanner;
    }

    public void compile() {
        Parser parser = new Parser();
        try {
            LinkedList<Token> tokens = new lexicalAnalyzer(this.scanner).getTokens();
            LinkedList<Pair<Integer, Token>> abstractSyntaxTree = parser.generateAbstractSyntaxTree(tokens);
            new CodeGenerator(abstractSyntaxTree).generateCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
