package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import Log.Log;
import javafx.util.Pair;
import scanner.lexicalAnalyzer;
import scanner.token.Token;

public class Parser {
    ArrayList<Rule> rules;
    Stack<Integer> parsStack;
    ParseTable parseTable;
    lexicalAnalyzer lexicalAnalyzer;

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
//        cg = new CodeGenerator();
    }

    Token lookAhead;
    boolean finish;
    LinkedList<Pair<Integer, Token>> abstractSyntaxTree;
    public LinkedList<Pair<Integer, Token>> generateAbstractSyntaxTree(java.util.Scanner sc) throws Exception {
        lexicalAnalyzer = new lexicalAnalyzer(sc);
        lookAhead = lexicalAnalyzer.getNextToken();
        finish = false;
        abstractSyntaxTree = new LinkedList<>();
        Action currentAction;
        while (!finish) {
            try {
                Log.print(/*"lookahead : "+*/ lookAhead.toString() + "\t" + parsStack.peek());
//                Log.print("state : "+ parsStack.peek());
                currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
                Log.print(currentAction.toString());
                //Log.print("");
                currentAction.act(this);
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
//                boolean find = false;
//                for (NonTerminal t : NonTerminal.values()) {
//                    if (parseTable.getGotoTable(parsStack.peek(), t) != -1) {
//                        find = true;
//                        parsStack.push(parseTable.getGotoTable(parsStack.peek(), t));
//                        StringBuilder tokenFollow = new StringBuilder();
//                        tokenFollow.append(String.format("|(?<%s>%s)", t.name(), t.pattern));
//                        Matcher matcher = Pattern.compile(tokenFollow.substring(1)).matcher(lookAhead.toString());
//                        while (!matcher.find()) {
//                            lookAhead = lexicalAnalyzer.getNextToken();
//                        }
//                    }
//                }
//                if (!find)
//                    parsStack.pop();
            }
        }
        return abstractSyntaxTree;
//        if (!ErrorHandler.hasError) {
////            cg.printMemory();
//            cg.testMemory();
//        }
    }
}
