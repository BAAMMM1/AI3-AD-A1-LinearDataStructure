package task4;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse stellt den Algorithmus zur Verarbeitung vollständig geklammerter Ausdrücke da.
 *
 * @author Chris on 12.04.2018
 */
public class ExpressionAlgorithmus {

    private Stack<Token> stack;
    
    private static final String SIGN_PLUS = "+";
    private static final String SIGN_MINUS = "-";
    private static final String SIGN_MULT = "*";
    private static final String SIGN_DIV = "/";
    private static final String SIGN_STOP = ")";


    public ExpressionAlgorithmus() {
        this.stack = new Stack<Token>();
    }

    private int execute(String expression) {

        ArrayList<Token> tokens = this.parseTokens(expression);

        Token operand2;
        Token operator;
        Token operand1;

        for (int i = 0; i < tokens.size(); i++) {

            Token nextToken = tokens.get(i);

            if (!(nextToken.getValue().equals(SIGN_STOP))) {

                this.stack.push(nextToken);

            } else {
                operand2 = this.stack.pop();
                operator = this.stack.pop();
                operand1 = this.stack.pop();

                // entfernen von ( vom Stack
                this.stack.pop();

                // Berechnen

                int result = 0;
                if (operator.getValue().equals(SIGN_PLUS)) {
                    System.out.println(operand1 + "+" + operand2);
                    result = operand1.getIntegerValue() + operand2.getIntegerValue();

                } else if (operator.getValue().equals(SIGN_MINUS)) {
                    System.out.println(operand1 + "-" + operand2);
                    result = operand1.getIntegerValue() - operand2.getIntegerValue();

                } else if (operator.getValue().equals(SIGN_MULT)) {
                    System.out.println(operand1 + "*" + operand2);
                    result = operand1.getIntegerValue() * operand2.getIntegerValue();

                } else if (operator.getValue().equals(SIGN_DIV)) {
                    System.out.println(operand1 + "/" + operand2);
                    result = operand1.getIntegerValue() / operand2.getIntegerValue();
                }

                System.out.println("result: " + result);

                this.stack.push( new Token(String.valueOf(result)));

            }

            System.out.println(this.stack.toString());




        }

        return this.stack.pop().getIntegerValue();

    }


    private ArrayList<Token> parseTokens(String expression){

        ArrayList<Token> tokens = new ArrayList<Token>();

        for(int i = 1; i <= expression.length(); i++) {

            try{

                Integer.valueOf(expression.substring(0,i));
                Integer.valueOf(expression.substring(0,i+1));

            } catch (Exception e){

                tokens.add(new Token(expression.substring(0,i)));

                expression = expression.substring(i);

                i = 0;

            }

        }


        System.out.println("tokens: " + tokens.toString());

        return tokens;



    }

    public static void main(String[] args) {

        System.out.println(new ExpressionAlgorithmus().execute("(((1+2)*3)-(7*8))"));
        System.out.println(new ExpressionAlgorithmus().execute("(((11+2)*3)-(7*8))"));

    }

    private static class Token{

        String value;

        public Token(String value) {
            this.value = value;
        }

        public int getIntegerValue(){
            return Integer.valueOf(value);
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }


}
