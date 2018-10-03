package calc;

import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        // TODO List<String> tokens = tokenize(expr);
        // TODO List<String> postfix = infix2Postfix(tokens);
        // TODO double result = evalPostfix(postfix);
        return 0; // result;
    }

    // ------  Evaluate RPN expression -------------------

    // TODO Eval methods

    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    // TODO Methods

    List infix2Postfix(List tokenizedExpr) {

        for (int i = 0; i < tokenizedExpr.size(); i++) {



        }


        return null;
    }

    int getHighestPriority(List<String> tokenizedExpr) {
        int highest = 1;
        for (int i = 0; i < tokenizedExpr.size(); i++) {
            if (getPrioritization(tokenizedExpr.get(i)) > highest) {
                highest = getPrioritization(tokenizedExpr.get(i));
            }
        }
        return highest;
    }

    int getPrioritization(String token) {
        if ("(".contains(token)) {
            return 4;
        }
        if ("^".contains(token)) {
            return 3;
        }
        if ("*/".contains(token)) {
            return 2;
        }
        if ("+-".contains(token)) {
            return 1;
        }
        return -1;
    }

    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }


    // ---------- Tokenize -----------------------

    List tokenize(String expr) {
        expr = expr.trim();
        String[] exprAsStringArr = expr.split("");
        List finalStrings = createTokenizedList(exprAsStringArr);

        return finalStrings;
    }

    List createTokenizedList(String[] sArr) {
        List tokenizedList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        boolean isLastIndex = false;

        for (int i = 0; i < sArr.length; i++) {
            isLastIndex = i == sArr.length - 1;
            checkValidity(sArr[i], tokenizedList, sb, isLastIndex);
        }
        return tokenizedList;
    }


    void checkValidity(String s, List tokenizedList, StringBuilder number, boolean isLastIndex) {//TODO ++går inte.
        if (isNumber(s)) {
            number.append(s);
            if (isLastIndex) {
                tokenizedList.add(number.toString());
            }

        } else if (isOperator(s)) { //är det en operator vill vi lägga till den också
            addNumber2TokenizedList(tokenizedList, number);
            tokenizedList.add(s);

        } else if (isParantheses(s)) { //är det en operator vill vi lägga till den också
            addNumber2TokenizedList(tokenizedList, number);
            tokenizedList.add(s);

        } else if (!(isWhiteSpace(s))) {
            addNumber2TokenizedList(tokenizedList, number);
        }
    }

    void addNumber2TokenizedList(List tokenizedList, StringBuilder sb) {
        if (sb.length() > 0) {
            tokenizedList.add(sb.toString());
        }
        sb.setLength(0); //clears the StringBuilder
    }

    boolean isParantheses(String s) {
        String numb = "()[]{}";
        return numb.indexOf(s) >= 0;
    }

    boolean isOperator(String s) {
        String numb = "+-*/^";
        return numb.indexOf(s) >= 0;
    }

    boolean isWhiteSpace(String s) {
        return s.equals(" ");
    }

    boolean isNumber(String s) {
        String numb = "0123456789";
        return numb.indexOf(s) >= 0;
    }

}