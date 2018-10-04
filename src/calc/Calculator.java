package calc;

import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;
import static java.lang.Math.toIntExact;


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

    List infix2Postfix(List<String> infixExpr) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> postFix = new ArrayList<>();

        for (int i = 0; i < infixExpr.size(); i++) {
            String token = infixExpr.get(i);

            if (isValue(token)) {
                postFix.add(token);
            } else if (isOperator(token)) {

                if (stack.isEmpty()) {
                    stack.push(token);
                } else {

                    if (checkIfPop(stack, token)) {
                        popOperators(stack, postFix);
                    }
                    stack.push(token);
                }
            }else if(isLeftParantheses(token)){
                stack.push(token);

            }else if(isRightParantheses(token)){
                while (!isLeftParantheses(stack.peek())){//While the top-stack is not a "("
                    postFix.add(stack.pop());
                }
                stack.pop();//Remove left parantheses
            }
        }

        stack2PostFix(stack, postFix);

        return postFix;
    }

    //Empties stack and put all elements to postFix
    void stack2PostFix(Deque<String> stack, List<String> postFix) {
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                postFix.add(stack.pop());
            }
        }
    }

    boolean isValue(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean checkIfPop(Deque<String> stack, String token) {

        if (!isLeftParantheses(token)) {
            if (getPrecedence(token) < getPrecedence(stack.peek())) { //Check if priority is less than whats already on the top    //TODO Hur gör vi med if???
                return true;
            } else if (getPrecedence(token) == getPrecedence(stack.peek()) && getAssociativity(stack.peek()) == Assoc.LEFT) { //If its the same and the top has left associativity
                return true;
            }
        }
        return false;
    }

    boolean isLeftParantheses(String s) {
        String par = "([{";
        return par.indexOf(s) >= 0;
    }

    boolean isRightParantheses(String s) {
        String par = ")]}";
        return par.indexOf(s) >= 0;
    }

    void popOperators(Deque<String> stack, List<String> postFix) {
        for (int i = 0; i < stack.size(); i++) {
            postFix.add(stack.pop());
        }

    }

    int getPrecedence(String op) {
        if ("()[]{}".indexOf(op) >= 0) {
            return 1;
        }else if ("+-".contains(op)) {
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
        List infixExpr = createTokenizedList(exprAsStringArr);

        return infixExpr;
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

        } else if (isParantheses(s)) {
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