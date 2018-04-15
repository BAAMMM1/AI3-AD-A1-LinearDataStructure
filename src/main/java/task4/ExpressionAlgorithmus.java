package task4;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse stellt den Algorithmus zur Verarbeitung vollständig geklammerter Ausdrücke da.
 *
 * @author Chris on 12.04.2018
 */
public class ExpressionAlgorithmus {

    private Stack<String> stack;
    
    private static final String SIGN_PLUS = "+";
    private static final String SIGN_MINUS = "-";
    private static final String SIGN_MULT = "*";
    private static final String SIGN_DIV = "/";
    private static final String SIGN_STOP = ")";

    // TODO - einmal normale, einmal rekursiv


    public ExpressionAlgorithmus() {
        this.stack = new Stack<String>();
    }

    private void execute(String expression) {

        ArrayList<String> tokens = this.parseTokens(expression);

        String operand2;
        String operator;
        String operand1;

        for (int i = 0; i < tokens.size(); i++) {

            String nextToken = tokens.get(i);

            if (!(nextToken.equals(SIGN_STOP))) {

                this.stack.push(nextToken);

            } else {
                operand2 = this.stack.pop();
                operator = this.stack.pop();
                operand1 = this.stack.pop();

                // entfernen von ( vom Stack
                this.stack.pop();

                // Berechnen

                int result = 0;
                if (operator.equals(SIGN_PLUS)) {
                    System.out.println(operand1 + "+" + operand2);
                    result = Integer.valueOf(operand1) + Integer.valueOf(operand2);

                } else if (operator.equals(SIGN_MINUS)) {
                    System.out.println(operand1 + "-" + operand2);
                    result = Integer.valueOf(operand1) - Integer.valueOf(operand2);

                } else if (operator.equals(SIGN_MULT)) {
                    System.out.println(operand1 + "*" + operand2);
                    result = Integer.valueOf(operand1) * Integer.valueOf(operand2);

                } else if (operator.equals(SIGN_DIV)) {
                    System.out.println(operand1 + "/" + operand2);
                    result = Integer.valueOf(operand1) / Integer.valueOf(operand2);
                }

                System.out.println("result: " + result);



                this.stack.push( Integer.toString(result));

            }

            System.out.println(this.stack.toString());


        }


    }

    private String parseNextToken(String expression){

        String token = expression.substring(0, 1);

        int i = 0;

        while(i < expression.length()){

            try {

                Integer.valueOf(expression.substring(i, i + 1));

                token = expression.substring(0, i+1);

            } catch (NumberFormatException e){
                System.out.println("token: " + token);
                return  token;
            }

            i++;
        }

        System.out.println("token: " + token);

        return  token;

    }

    private ArrayList<String> parseTokens(String expression){

        ArrayList<String> tokens = new ArrayList<String>();

        for(int i = 1; i <= expression.length(); i++) {

            try{

                Integer.valueOf(expression.substring(0,i));
                Integer.valueOf(expression.substring(0,i+1));

            } catch (Exception e){

                tokens.add(expression.substring(0,i));

                expression = expression.substring(i);

                i = 0;

            }

        }


        System.out.println("tokens: " + tokens.toString());

        return tokens;



    }

    public static void main(String[] args) {

        new ExpressionAlgorithmus().execute("(((1+2)*3)-(7*8))");
        new ExpressionAlgorithmus().execute("(((11+2)*3)-(7*8))");

        //System.out.println(new ExpressionAlgorithmus().parseNextToken("115+1"));

        //new ExpressionAlgorithmus().parseTokens("(((11+2)*3)-(7*8))");

    }

    private static class Token{

        char[] value;

        public Token(char[] value) {
            this.value = value;
        }

        public int getIntegerValue(){
            return 0;
        }
    }


}
