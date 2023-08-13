package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import Log.Log;
import errorHandler.ErrorHandler;
import javafx.util.Pair;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

public class Parser {
    ArrayList<Rule> rules;
    Stack<Integer> parsStack;
    ParseTable parseTable;

    public Parser() {
        parsStack = new Stack<Integer>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    LinkedList<Token> tokens;
    Token lookAhead;
    boolean finish;
    LinkedList<Pair<Integer, Token>> abstractSyntaxTree;
    public LinkedList<Pair<Integer, Token>> generateAbstractSyntaxTree(LinkedList<Token> tokens) throws Exception {
        this.tokens = tokens;
        lookAhead = tokens.pop();
        finish = false;
        abstractSyntaxTree = new LinkedList<>();
        Action currentAction;
        while (!finish) {
            currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
            currentAction.act(this);
        }
        return abstractSyntaxTree;
    }
}
