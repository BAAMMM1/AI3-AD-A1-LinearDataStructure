package task5;

import java.util.Arrays;

/**
 * Diese Klasse stellt den Algortihmus dar, der aus einem ganzahligen Array das Minimum und das Maximum ermittelt.
 *
 * @author Chris on 12.04.2018
 */
public class MinMaxAlgortihmus {

    private int min;
    private int max;
    private int comparisons;

    public MinMaxAlgortihmus(int[] array) {

        this.compute(array);

    }

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

        System.out.println("*---------------------*");
        System.out.println(Arrays.toString(array));
        System.out.println("Vergleiche: " + comparisons);
        System.out.println("max: " + this.max);
        System.out.println("min: " + this.min);

    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getComparisons() {
        return comparisons;
    }

}
