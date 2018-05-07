package task4;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Diese Klasse stellt den Algorithmus zur Verarbeitung vollständig geklammerter Ausdrücke da.
 *
 * @author Chris on 12.04.2018
 */
public class ExpressionAlgorithmus {

    private static final String SIGN_STOP = ")";

    private Stack<String> stack;

    public ExpressionAlgorithmus() {
        this.stack = new Stack<String>();
    }

    /**
     * Führt für den übergebenen Ausruck die stackbasierte Auswertung aus
     * Operation compute:   ExpressionAlgorithmus x String -> INT
     * pre:                 Es sind folgende Terme erlaubt:
     *                      - positive ganze Zahlen
     *                      - Klammern ()
     *                      - Grundoperationen +−∗/
     *                      - Ausdrücke müssen vollständig geklammert sein
     *                      - Operationen haben genau zwei Operanden
     *                      - Operanden sind Zahlen oder Klammer-Ausdrücke
     *                      - keine Punkt- vor Strichrechnung
     * post:
     * @param expression Überprüfung auf zugelassenen, reguläreren Ausdruck
     * @return int
     * @throws IllegalArgumentException falls Ausdruck nicht zulässig
     */
    public int compute(String expression) throws IllegalArgumentException {

        // Check ob der String den Bedingungen entsprecht
        this.preconditionCheck(expression);

        // String in Tokens zerteilen
        ArrayList<String> tokens = this.parseTokens(expression);

        for (String nextToken : tokens) {

            if (!(nextToken.equals(SIGN_STOP))) {

                this.stack.push(nextToken);

            } else {
                // Operanden und Operator vom Stack holen
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

        return Integer.valueOf(this.stack.pop());

    }


    private ArrayList<String> parseTokens(String expression) {

        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 1; i <= expression.length(); i++) {

            try {

                // Klammer- und Operatorprüfung
                Integer.valueOf(expression.substring(0, i)); // sonst auch das möglich +2, da + nicht als Operator erkannt wird

                // Operanden bis zu ")" und Operator ermitteln; +1 bedeutet hierbei auch, das nicht 2 Operatoren aufeinander folgen können
                Integer.valueOf(expression.substring(0, i + 1)); // Für mehrstellige Zahlen, also 10, 100, 1000

            } catch (Exception e) {

                tokens.add(expression.substring(0, i));

                expression = expression.substring(i);

                // Nach gefundenem Symbol (1 bis x Zeichen lang, wobei x Anzahl an Stellen bei einer Zahl entspricht),
                // muss wieder mit einer Länge von 1 gesucht werden (daher hier auf 0 und in for-Schleife wieder ++)
                // expression wird durch substring wieder um
                i = 0;

            }

        }

        System.out.println("tokens: " + tokens.toString());

        return tokens;

    }

    private void preconditionCheck(String toCheck) {

        Pattern pattern = Pattern.compile("\\([0-9]+[+|\\-|*|/][0-9]+\\)");

        while (pattern.matcher(toCheck).find()) {
            toCheck = pattern.matcher(toCheck).replaceAll("5");
        }

        try {
            Integer.valueOf(toCheck);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("incorrect string");
        }


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
        }

        private int calculate(Operand operand1, Operand operand2) {

            if (this.value.equals(SIGN_PLUS)) {
                return operand1.getIntegerValue() + operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MINUS)) {
                return operand1.getIntegerValue() - operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_MULT)) {
                return operand1.getIntegerValue() * operand2.getIntegerValue();

            } else if (this.value.equals(SIGN_DIV)) {
                return operand1.getIntegerValue() / operand2.getIntegerValue();
            } else {
                throw new IllegalArgumentException("operator not found");
            }


        }
    }


}
