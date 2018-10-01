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

    List tokenize(String input) {
        input = input.trim();
        String[] sArr = input.split("");
        StringBuilder sb = new StringBuilder();
        List finalStrings = createFinalString(sArr, sb);

        return finalStrings;
    }

    List createFinalString(String[] sArr, StringBuilder sb) {
        List finalStrings = new ArrayList();

        for (int i = 0; i < sArr.length; i++) {
            checkValidity(sArr[i], finalStrings, sb);
        }

        checkValidityOnLast(sArr[sArr.length-1], finalStrings, sb);
        return finalStrings;
    }

    void checkValidityOnLast(String s, List finalStrings, StringBuilder sb){
        if(isNumber(s)){
            finalStrings.add(sb.toString());// för att få med den sista termen
        }
    }

    void checkValidity(String s, List finalStrings, StringBuilder sb){
        if (isNumber(s)) {
            sb.append(s);

        } else if (isOperator(s)) { //är det en operator vill vi lägga till den också
            addSb2FinalStrings(finalStrings, sb);
            finalStrings.add(s);

        } else if (isPara(s)) { //är det en operator vill vi lägga till den också
            addSb2FinalStrings(finalStrings, sb);
            finalStrings.add(s);

        } else if (!(isWhiteSpace(s))) {
            addSb2FinalStrings(finalStrings, sb);
        }
    }

    void addSb2FinalStrings(List finalStrings, StringBuilder sb){
        if (sb.length() > 0) {
            finalStrings.add(sb.toString());
        }
        sb.setLength(0); //clears the StringBuilder
    }

    boolean isPara(String s) {
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











