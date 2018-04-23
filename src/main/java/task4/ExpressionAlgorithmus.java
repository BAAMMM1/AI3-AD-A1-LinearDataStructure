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

    public int compute(String expression) throws IllegalArgumentException {

        ArrayList<String> tokens = this.parseTokens(expression);

        for (String nextToken : tokens) {

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

                this.stack.push(String.valueOf(result));
            }

            System.out.println(this.stack.toString());
        }

        int result = Integer.valueOf(this.stack.pop());

        if (!this.stack.isEmpty()) throw new IllegalArgumentException();

        return result;

    }


    private ArrayList<String> parseTokens(String expression) {

        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 1; i <= expression.length(); i++) {

            try {

                Integer.valueOf(expression.substring(0, i));
                Integer.valueOf(expression.substring(0, i + 1));

            } catch (Exception e) {

                tokens.add(expression.substring(0, i));

                expression = expression.substring(i);

                i = 0;

            }

        }

        System.out.println("tokens: " + tokens.toString());

        return tokens;

    }


    private static class Token {

        String value;

        private Token(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private static class Operand extends Token {

        private Operand(String value) {
            super(value);

            try {
                Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("incorrect operand");
            }
        }

        private int getIntegerValue() {
            return Integer.valueOf(value);
        }

    }

    private static class Operator extends Token {

        private static final String SIGN_PLUS = "+";
        private static final String SIGN_MINUS = "-";
        private static final String SIGN_MULT = "*";
        private static final String SIGN_DIV = "/";


        private Operator(String value) {
            super(value);

            if (!(value.equals(SIGN_PLUS) || value.equals(SIGN_MINUS) || value.equals(SIGN_MULT) || value.equals(SIGN_DIV))) {
                throw new IllegalArgumentException("incorrect operator");
            }
        }

        private int calculate(Operand operand1, Operand operand2) {
            int result = 0;

            if (this.value.equals(SIGN_PLUS)) {
                result = operand1.getIntegerValue() + operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MINUS)) {
                result = operand1.getIntegerValue() - operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MULT)) {
                result = operand1.getIntegerValue() * operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_DIV)) {
                result = operand1.getIntegerValue() / operand2.getIntegerValue();
            }

            return result;

        }
    }


}
