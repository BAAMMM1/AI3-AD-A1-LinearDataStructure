package task4;

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

        String operand2;
        String operator;
        String operand1;

        for (int i = 0; i < expression.length(); i++) {

            if (!expression.substring(i,i+1).equals(SIGN_STOP)) {

                this.stack.push(expression.substring(i,i+1));

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

        System.out.println("end result: " + this.stack.pop());


    }

    private void parseIntoTokens(String expression){




    }

    public static void main(String[] args) {

        new ExpressionAlgorithmus().execute("((((1+2)*3)-(7*8))");

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
