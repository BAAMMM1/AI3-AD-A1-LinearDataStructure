package task3;

import task1.ILinearList;
import task2.ArrayBasedList;
import task2.DoubleLinkedList;

import java.util.Random;

/**
 * @author Chris on 09.04.2018
 */
public class LinearListPerformanceTest {

    private static final int RUNS = 10;

    private static final int ARRAY_ELEMENTS_INSERT  = 20000;
    private static final int ARRAY_ELEMENTS_DELETE  = 20000;
    private static final int ELEMENT_DELETE_SIZE    = 1000;

    private static final int RANDOM_BOUND           = 999999999;

    private static final String[] QUALIFIER         = new String[]{"Start", "Random", "End"};
    private static final int[][] POSITIONS_INSERT   = new int[][]{getStartPositions(), getRandomPositions(false), getEndPositions(false)};
    private static final int[][] POSITIONS_DELETE   = new int[][]{getStartPositions(), getRandomPositions(true), getEndPositions(true)};

    private long[] resultsInsert;
    private long[] resultsDelete;


    public void comparison(ILinearList<Integer>... lists) {

        this.insertSeries(QUALIFIER, POSITIONS_INSERT, lists);

        this.deleteSeries(QUALIFIER, POSITIONS_DELETE, lists);
        
        this.printComparisonResult();

    }


    private void insertSeries(String[] qualifier, int[][] posInsert, ILinearList<Integer>... lists){

        if(QUALIFIER.length != POSITIONS_INSERT.length) throw new IllegalArgumentException("must be the same size");

        resultsInsert = new long[posInsert.length*2];

        System.out.println("*-------------------- insert series -----------------------*: ");

        for(int i = 0; i < lists.length; i++){

            System.out.println(lists[i].getClass().getSimpleName() + ":");

            for(int j = 0; j < posInsert.length; j++){
                System.out.print(qualifier[j] + ":\t");
                resultsInsert[j] = this.insertTest(posInsert[j], lists[i]);
                System.out.println("\t av.: " + resultsInsert[j]);
            }

            System.out.println("*----------------------------------------------------------*");

        }

        System.out.println();

    }

    private void deleteSeries(String[] qualifier, int[][] posDelete, ILinearList<Integer>... lists){

        if(QUALIFIER.length != POSITIONS_DELETE.length) throw new IllegalArgumentException("must be the same size");

        resultsDelete = new long[posDelete.length*2];

        System.out.println("*-------------------- delete series -----------------------*: ");

        for(int i = 0; i < lists.length; i++){
            System.out.println(lists[i].getClass().getSimpleName() + ":");


            for(int j = 0; j < posDelete.length; j++){

                System.out.print(qualifier[j] + ":\t");

                resultsDelete[j] = this.deleteTest(posDelete[j], lists[i]);

                System.out.println("\t av.: " + resultsDelete[j]);
            }

            System.out.println("*----------------------------------------------------------*");

        }

    }


    private long insertTest(int[] positions, ILinearList<Integer> list) {

        long[] results = new long[RUNS];

        for (int index = 0; index < RUNS; index++) {

            list.clean();

            long start = System.currentTimeMillis();

            for (int i = 0; i < ARRAY_ELEMENTS_INSERT; i++) {
                list.insert(positions[i], this.getRandomInteger());
            }

            long end = System.currentTimeMillis();

            System.out.print((end - start) + "\t");

            results[index] = end - start;

        }

        // Durchschnitt der Messungen berechnen
        long temp = 0;

        for (int i = 0; i < results.length; i++) {
            temp += results[i];

        }

        long median = temp / RUNS;

        return median;

    }

    private long deleteTest(int[] positions, ILinearList<Integer> list) {


        long[] results = new long[RUNS];

        for (int index = 0; index < RUNS; index++) {

            list.clean();

            // 1. Liste befüllen von hinten (Geht am schnellsten bei beiden)
            for (int i = 0; i < ARRAY_ELEMENTS_DELETE; i++) {
                list.insert(i, this.getRandomInteger());
            }

            long start = System.currentTimeMillis();

            for (int i = 0; i < ELEMENT_DELETE_SIZE; i++) {
                list.delete(positions[i]);
            }

            long end = System.currentTimeMillis();

            System.out.print((end - start) + "\t");

            results[index] = end - start;

        }

        // Durchschnitt der Messungen berechnen
        long temp = 0;

        for (int i = 0; i < results.length; i++) {
            temp += results[i];

        }

        long median = temp / RUNS;

        return median;

    }

    private void printComparisonResult() {

        // TODO - Listen miteinander Vergleichen

    }


    private static int[] getStartPositions() {
        return new int[ARRAY_ELEMENTS_INSERT];
    }

    /**
     * falls reverse false random bound aufsteigend
     * falls reverse true random bound absteigend
     *
     * @param reverse
     * @return
     */
    private static int[] getRandomPositions(boolean reverse) {
        int[] result = new int[ARRAY_ELEMENTS_INSERT];

        for (int i = 0; i < ARRAY_ELEMENTS_INSERT; i++) {

            if (reverse) {
                result[i] = new Random().nextInt((ARRAY_ELEMENTS_INSERT) - i);
            } else {
                result[i] = new Random().nextInt(i + 1);
            }
        }
        return result;
    }

    /**
     * reverse false -> 0 1 2 3 4 5
     * reverse true -> 5 4 3 2 1 0
     *
     * @param reverse
     * @return
     */
    private static int[] getEndPositions(boolean reverse) {

        int[] result = new int[ARRAY_ELEMENTS_INSERT];

        for (int i = 0; i < ARRAY_ELEMENTS_INSERT; i++) {
            if (reverse) {
                result[i] = (ARRAY_ELEMENTS_INSERT - 1) - i;
            } else {
                result[i] = i;
            }
        }
        return result;
    }


    private Integer getRandomInteger() {
        Random random = new Random();
        return new Integer(random.nextInt(RANDOM_BOUND));
    }

    public static void main(String[] args) {

        LinearListPerformanceTest pTest = new LinearListPerformanceTest();

        pTest.comparison(new ArrayBasedList<Integer>(), new DoubleLinkedList<Integer>());
    }


}
