package task5;

import java.util.Arrays;

/**
 * Diese Klasse stellt den Algortihmus dar, der aus einem ganzahligen Array das Minimum und das Maximum ermittelt.
 *
 * @author Chris on 12.04.2018
 */
public class MinMaxAlgortihmus {

    /**
     * Ermitteltes Minimum
     */
    private int min;

    /**
     * Ermitteltes Maximum
     */
    private int max;

    /**
     * Benötigte Vergleiche
     */
    private int comparisons;

    /**
     * Array auf das der Algortihmus angewandt wurde
     */
    private int[] array;

    /**
     * Erstellt ein neue MinMaxAlgorithmus-Objekt und wendet des Algorithmus auf das übergebene Array an.
     *
     * @param array nicht null und array length > 0
     */
    public MinMaxAlgortihmus(int[] array) {

        if (array == null) throw new IllegalArgumentException("array must not be null");
        if(array.length == 0) throw new IllegalArgumentException();

        this.array = array;
        this.compute(array);

    }

    /**
     * Berechnet das Minimum und Maximum.<br>
     * Diese Methode enthält die Handlungsvorschrift des Algorithmus.
     * Operation compute:   MinMaxAlgortihmus x ARRAY
     * pre:                 Array length > 0
     * post:
     *
     * @param array nicht null und length > 0
     */
    private void compute(int[] array) {

        if(array == null) throw new IllegalArgumentException();
        if(array.length == 0) throw new IllegalArgumentException();

        this.min = array[0];
        this.max = array[0];

        int a;
        int b;

        comparisons = 0;

        // TODO - 3/2n -1 bei jeweils 2 Elemente anschauen

        for (int i=1; i< array.length; i=i+2)
        {
            a = array[i];
            try {
                b = array[i + 1];
            }
            catch(Exception e){
                comparisons = comparisons +1;
                if (a < this.min)min = a;
                comparisons = comparisons +1;
                if (a > this.max)max = a;
                continue;
            }
            comparisons = comparisons + 1;
            if ((a < b)) {
                comparisons = comparisons+1;
                if ((a < this.min)) {
                    this.min = a;
                }
                comparisons = comparisons+1;
                if (b>this.max){
                    this.max = b;
                }
                continue;

            } else {
                comparisons = comparisons+1;
                if (((b < this.min))) {
                    this.min = b;
                }
                comparisons = comparisons+1;
                if (a>this.max){
                    this.max = a;
                }
            }

        }

        System.out.println("*---------------------*"
                + "\n" + Arrays.toString(array)
                + "\nVergleiche: " + comparisons
                + "\nmax: " + this.max
                + "\nmin: " + this.min
        );


    }

    /**
     * Gibt das ermittelte Minimum des ganzahligen Feldes zurück.
     *
     * @return kleinster Wert des Arrays
     */
    public int getMin() {
        return min;
    }

    /**
     * Gibr das ermittelte Maximum des ganzahligen Feldes zurück.
     *
     * @return größter Wert des Arrays
     */
    public int getMax() {
        return max;
    }

    /**
     * Gibt die Anzahl an benötigten Vergleiche des Algortihmus an.
     *
     * @return Vergleichsanzahl
     */
    public int getComparisons() {
        return comparisons;
    }

    /**
     * Gibt das Array für die das Minimum und das Maximum ermittelt wurde zurück.
     *
     * @return Feld auf das der Algortihmus angewendet wurde
     */
    public int[] getArray() {
        return array;
    }
}
