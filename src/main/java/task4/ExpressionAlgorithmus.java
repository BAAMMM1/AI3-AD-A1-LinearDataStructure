package task4;

import java.util.ArrayList;

/**
 * Diese Klasse stellt den Algorithmus zur Verarbeitung vollständig geklammerter Ausdrücke da.
 *
 * @author Chris on 12.04.2018
 */
public class ExpressionAlgorithmus {

    private Stack<String> stack;

    private static final String SIGN_STOP = ")";


    public ExpressionAlgorithmus() {
        this.stack = new Stack<String>();
    }

    private int execute(String expression) throws IllegalArgumentException {

        ArrayList<String> tokens = this.parseTokens(expression);


        for (int i = 0; i < tokens.size(); i++) {

            String nextToken = tokens.get(i);

            if (!(nextToken.equals(SIGN_STOP))) {

                this.stack.push(nextToken);

            } else {
                Operand operand2 = new Operand(this.stack.pop());
                Operator operator = new Operator(this.stack.pop());
                Operand operand1 = new Operand(this.stack.pop());

                // entfernen von ( vom Stack
                this.stack.pop();

                // Berechnen

                int result = operator.calculate(operand1, operand2);


                this.stack.push( String.valueOf(result));

            }

            System.out.println(this.stack.toString());




        }

        return Integer.valueOf(this.stack.pop());

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

        System.out.println("result: " + new ExpressionAlgorithmus().execute("(((1+2)*3)-(7*8))"));
        System.out.println("result: " + new ExpressionAlgorithmus().execute("(((11+2)*3)-(7*8))"));

        System.out.println("result: " + new ExpressionAlgorithmus().execute("(1+2)"));

    }

    private static class Token{

        String value;

        public Token(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static class Operand extends Token{

        public Operand(String value) {
            super(value);

            try{
                Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("incorrect string");
            }

        }

        public int getIntegerValue(){
            return Integer.valueOf(value);
        }

    }

    private static class Operator extends Token{

        private static final String SIGN_PLUS = "+";
        private static final String SIGN_MINUS = "-";
        private static final String SIGN_MULT = "*";
        private static final String SIGN_DIV = "/";


        public Operator(String value) {
            super(value);
        }

        public int calculate(Operand operand1, Operand operand2){
            int result = 0;

            if (this.value.equals(SIGN_PLUS)) {
                result = operand1.getIntegerValue() + operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MINUS)) {
                result = operand1.getIntegerValue() - operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MULT)) {
                result = operand1.getIntegerValue() * operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_DIV)) {
                result = operand1.getIntegerValue() / operand2.getIntegerValue();
            } else {
                throw new IllegalArgumentException("incorrect string");
            }

            return result;

        }
    }


}
