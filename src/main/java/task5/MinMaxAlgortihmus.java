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
     * @param array Array auf dem der Algorithmus angewendet wird
     */
    public MinMaxAlgortihmus(int[] array) {

        if (array == null) throw new IllegalArgumentException("array must not be null");

        this.array = array;
        this.compute(array);

    }

    /**
     * Berechnet das Minimum und Maximum.<br>
     * Diese Methode enthält die Handlungsvorschrift des Algorithmus.
     *
     * @param array Feld für das das Minimum und das Maximum berechnet wird
     */
    private void compute(int[] array) {

        this.min = array[0];
        this.max = array[0];

        comparisons = 0;

        for (int i = 1; i < array.length; i++) {

            comparisons++;
            if (this.max < array[i]) {
                this.max = array[i];
                continue;
            }

            comparisons++;
            if (this.min > array[i]) {
                this.min = array[i];
                continue;
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
