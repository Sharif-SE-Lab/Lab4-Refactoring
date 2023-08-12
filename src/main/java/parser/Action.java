package parser;

import Log.Log;
import javafx.util.Pair;

import java.util.Stack;

public interface Action {
    String toString();

    void act(Parser parser);
}

class ShiftAction implements Action {
    private final int stateNumber;
    public ShiftAction(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    @Override
    public String toString() {
        return "s" + stateNumber;
    }

    @Override
    public void act(Parser parser) {
        parser.parsStack.push(stateNumber);
        parser.lookAhead = parser.lexicalAnalyzer.getNextToken();
    }
}

class ReduceAction implements Action {
    private final int ruleNumber;

    public ReduceAction(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    @Override
    public String toString() {
        return "r" + ruleNumber;
    }

    @Override
    public void act(Parser parser) {
        Rule rule = parser.rules.get(ruleNumber);
        Stack<Integer> parsStack = parser.parsStack;
        for (int i = 0; i < rule.RHS.size(); i++) {
            parsStack.pop();
        }

        Log.print(/*"state : " +*/ parsStack.peek() + "\t" + rule.LHS);
//                        Log.print("LHS : "+rule.LHS);
        parsStack.push(parser.parseTable.getGotoTable(parsStack.peek(), rule.LHS));
        Log.print(/*"new State : " + */parsStack.peek() + "");
//                        Log.print("");
        try {
            parser.abstractSyntaxTree.add(new Pair<>(rule.semanticAction, parser.lookAhead));
//            parser.cg.semanticFunction(rule.semanticAction, parser.lookAhead);
        } catch (Exception e) {
            Log.print("Code Genetator Error");
        }
    }
}

class AcceptAction implements Action {
    @Override
    public String toString() {
        return "acc";
    }

    @Override
    public void act(Parser parser) {
        parser.finish = true;
    }
}