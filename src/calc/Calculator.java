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

    List tokenize(String input){
        char[] cArr = input.toCharArray();
        cArr = removeWhiteSpace(cArr);
        StringBuilder sb = new StringBuilder();
        List finalStrings = new ArrayList();

        for (int i = 0; i < cArr.length; i++) {
            if(isNumber(cArr[i])){
                sb.append(cArr[i]);
            }else{
                finalStrings.add(sb.toString());
                sb.setLength(0); //clears the StringBuilder
                finalStrings.add(cArr[i]); //är det inte ett numer är det en operator
            }
        }
        finalStrings.add(sb.toString());// för att få med den sista termen

        return finalStrings;
    }

    boolean isWhiteSpace(char c){
        return Character.isWhitespace(c);
    }

    char[] removeWhiteSpace(char[] c){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if(!isWhiteSpace(c[i])){
                sb.append(c[i]);
            }
        }
        char[] arr = sb.toString().toCharArray();
        return  arr;
    }

    boolean isNumber(char c){
        char[] numb = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        for (int i = 0; i < numb.length; i++) {
            if(numb[i] == c){
                return true;
            }
        }
        return false;
    }


    char getNextCharInArr(char[] c, int index){
        if(c[index + 1] < c.length){
            return c[index+1];
        }else{
            return 'a';
        }
    }

    // TODO Methods to tokenize

}











