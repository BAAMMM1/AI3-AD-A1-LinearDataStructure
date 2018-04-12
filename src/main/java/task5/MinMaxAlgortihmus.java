package task5;

/**
 * Diese Klasse stellt den Algortihmus dar, der aus einem ganzahligen Array das Minimum und das Maximum ermittelt.
 *
 * @author Chris on 12.04.2018
 */
public class MinMaxAlgortihmus {


    public int max(int[] array){

        int result = array[0];
        int counter = 0;
        for(int i = 1; i < array.length; i++){

            if(result < array[i]){
                result = array[i];
            }

            counter++;

        }

        System.out.println("Vergleiche: " + counter);

        return result;

    }

    public int min(int[] array){

        int result = array[0];
        int counter = 0;
        for(int i = 1; i < array.length; i++){

            if(result > array[i]){
                result = array[i];
            }

            counter++;

        }

        System.out.println("Vergleiche: " + counter);

        return result;

    }

    public static void main(String[] args) {

        MinMaxAlgortihmus minMaxAlgortihmus = new MinMaxAlgortihmus();

        int[] array = new int[] {1,5,9,11,34,256,2,98,4,5};

        System.out.println(minMaxAlgortihmus.max(array));

        System.out.println(minMaxAlgortihmus.min(array));




    }

}
