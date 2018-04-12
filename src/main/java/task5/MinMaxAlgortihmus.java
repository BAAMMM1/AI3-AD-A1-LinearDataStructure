package task5;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Diese Klasse stellt den Algortihmus dar, der aus einem ganzahligen Array das Minimum und das Maximum ermittelt.
 *
 * @author Chris on 12.04.2018
 */
public class MinMaxAlgortihmus {

    private int min;
    private int max;

    public MinMaxAlgortihmus(int[] array) {

        this.execute(array);

    }

    private void execute(int[] array) {

        this.min = array[0];
        this.max = array[0];

        int counter = 0;

        for (int i = 1; i < array.length; i++) {

            counter++;
            if (this.max < array[i]) {
                this.max = array[i];
                continue;
            }

            counter++;
            if (this.min > array[i]) {
                this.min = array[i];
                continue;
            }

        }

        System.out.println("*---------------------*");
        System.out.println(Arrays.toString(array));
        System.out.println("Vergleiche: " + counter);
        System.out.println("max: " + this.max);
        System.out.println("min: " + this.min);

    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }


    public static void main(String[] args) {

        int[] array = new int[]{0, 5, 9, 11, 34, 256, 2, 98, 4, 5};
        int[] array2 = new int[]{256, 98, 34, 11, 8 , 4, 2, 1, 0};

        int[] array3 = new int[]{0,1,2,3,4,5,6,7,8,9};
        int[] array4 = new int[]{9,8,7,6,5,4,3,2,1,0};

        MinMaxAlgortihmus minMaxAlgortihmus = new MinMaxAlgortihmus(array);
        MinMaxAlgortihmus minMaxAlgortihmus2 = new MinMaxAlgortihmus(array2);
        MinMaxAlgortihmus minMaxAlgortihmus3 = new MinMaxAlgortihmus(array3);
        MinMaxAlgortihmus minMaxAlgortihmus4 = new MinMaxAlgortihmus(array4);




    }

}
