/**
 * Prepared for UVic CSC 115, Spring 2015
 * Assignment #4
 *
 * Michael Zastre
 * Methods evaluateExpression, evaluate, isBalanced were written by Dahv Reinhart - V00735279
 * All other methods (INCLUDING precedence, isOperator and toPostfix) were written exclusively by Michael Zastre.
 */

public class EvalInfix {
    /**
     * First ensure the arithmetic operations plus parentheses
     * are surrounded by spaces. Then go ahead and split up the
     * whole expression using spaces (i.e, the operands will be
     * nicely separated from everything else by at least a single
     * space). This will not work for negative numbers.
     */
    public static String[] tokenize(String s) {
        // Note the missing minus character...
        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/"};

        // First eliminate any quotation marks
        s = s.replaceAll("'", " ");
        s = s.replaceAll("\"", " ");

        // Now all operators or parentheses, surround the character
        // with a single space on either side.
        for (int i = 0; i < symbols.length; i++) {
            String regex = symbols[i];
            String replace = " " + regex + " ";
            s = s.replaceAll(regex, replace);
        }   

        // Special case: If there is a unary minus, then then
        // what appears to the right of the symbol is whitespace
        // and a digit; what appears to the left whitespace
        // and a non-digit symbol.
        s = s.replaceAll("(^|([\\+\\-\\*\\/]))\\s+\\-\\s+(\\d+)", "$1 -$3");

        // Eliminate extra whitespaces at start and end of the
        // transformed string. 
        s = s.trim();

        // Finally, take advantage of the whitespace to create an
        // array of strings where each item in the array is one
        // of the elements in the original string passed in to this
        // method.
        return s.split("\\s+");
    } 

    //This method is a master method that called all methods needed to compute the answer to the arithmetic
    //expression passed in at the command line.
    public static String evaluateExpression(String expr) {
        String result = null;
        try {
            isBalanced(expr); //expression must be balanced in terms of parenthesis.
            String[] tokens = tokenize(expr); //splits the expression up into tokens
            StringListRefBased postFix = new StringListRefBased(); 
            postFix = toPostfix(tokens); //the infix expression passed in is converted to postfix expr and stored in a list.
            result = evaluate(postFix); // computes the answer to the expression
        }
        catch (StringStackException e1) {
            System.err.println(e1);
            System.exit(-1);
        }
        return result;
    }
    //This method accepts the linked string list and computes the answer to the original arithmetic expression
    //based on the ordering of the nodes within this list. It sends a string to the caller containing the answer.
    public static String evaluate(StringListRefBased postFixExpr) throws StringStackException{
        String pos;
        String result = null;
        int op1 , op2 , res;
        StringStackRefBased eval = new StringStackRefBased();
        for (int i = 0; i < postFixExpr.getLength(); i++) { //goes through the postfix expr and computes the answer
            pos = postFixExpr.retrieve(i);
            if (!isOperator(pos)) { //is an operand
                eval.push(pos);
            }
            else {  //is an operator
                try {
                    try {
                        op1 = Integer.parseInt(eval.pop());
                        op2 = Integer.parseInt(eval.pop());
                    }
                    catch (StringStackException e2) {
                        throw new StringStackException("<syntax error>");
                    }
                    if (pos.equals("+"))
                        res = (op2 + op1);
                    else if (pos.equals("-"))
                        res = (op2 - op1);
                    else if (pos.equals("*"))
                        res = (op2 * op1);
                    else if (pos.equals("/")) {
                        if (op1 == 0)
                            throw new StringStackException("<devide by zero>");
                        res = (op2 / op1);
                    }
                    else
                        res = 0;
                    result = "" + res;
                    eval.push(result);
                }
                catch (NumberFormatException dd) {
                    System.out.println("StringStackException: <noninteger>");
                    System.exit(-4);
                }
                
            }
        }
        result = eval.pop();
        return result;
    }
    //This method tests to see if the forward and reverse braces match in terms of numbers.
    public static boolean isBalanced(String expr) throws StringStackException {
        int fBracket = 0;
        int rBracket = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(')
                fBracket++;
            if (expr.charAt(i) == ')')
                rBracket++;
        }
        if (fBracket == rBracket) //if they equal returns true
            return true;
        else 
            throw new StringStackException("<unbalanced ()>"); //if not a new exception is thrown
    }
    //This method tests to see if a particular character in the string is an operator or not (i.e. /+-*).
    //Written by MICHAEL ZASTRE
    public static boolean isOperator(String s) {
        String operators = "+-*/"; //possible operators

        if (s.length() != 1) {
            return false;
        }

        if (operators.indexOf(s) != -1) {
            return true; //must be an operator
        } else {
            return false; //must not be an operator
        }
    }
    //This method ranks the order with which the operations are done based on the rules of the PEDMAS system.
    //Written by MICHAEL ZASTRE
    public static int precedence(String s) {
        if (s.equals("/")) {
            return 4;
        } else if (s.equals("*")) {
            return 4;
        } else if (s.equals("-")) {
            return 3;
        } else if (s.equals("+")) {
            return 3;
        } else {
            return 0;
        }
    }
    //This method converts the passed in infix expression to a postfix expression.
    //Written by MICHAEL ZASTRE
    public static StringListRefBased toPostfix(String[] expression)
        throws StringStackException
    {
        StringStackRefBased operators = new StringStackRefBased();
        StringListRefBased postfixExpr = new StringListRefBased();

        for (int i = 0; i < expression.length; i++) {
            String s = expression[i];
 
            if (s.equals("(")) {
                operators.push(s);
            } else if (s.equals(")")) {
                while (true) {
                    String op = operators.pop();
                    if (op.equals("(")) {
                        break;
                    }
                    postfixExpr.insertTail(op);
                }
            } else if (isOperator(s)) {
                while (true) {
                    if (operators.isEmpty()) {
                        operators.push(s);
                        break;
                    }
                    if (operators.peek().equals("(")) {
                        operators.push(s);
                        break;
                    }
                    if (precedence(operators.peek()) < precedence(s)) {
                        operators.push(s);
                        break;
                    } 
                    String op = operators.pop();
                    postfixExpr.insertTail(op);
                }
            } else {
                postfixExpr.insertTail(s);
            }
        }
        while (!operators.isEmpty()) {
            postfixExpr.insertTail(operators.pop());
        }

        return postfixExpr;
    }
 
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("usage: java EvalInfix '<expression>'");
            System.exit(1);
        }

        System.out.println(evaluateExpression(args[0]));

    }
}
