package task3;

import task1.ILinearList;
import task2.ArrayBasedList;
import task2.DoubleLinkedList;

import java.util.Random;

/**
 * @author Chris on 14.04.2018
 */
public class LinearListPerformanceTestOld {

    private static final int RUNS = 10;

    private static final int ARRAY_ELEMENTS_INSERT = 20000;
    private static final int ARRAY_ELEMENTS_DELETE = 20000;
    private static final int ELEMENT_DELETE_SIZE = 1000;

    private static final int RANDOM_BOUND = 9999;


    public void insertTestSeries(ILinearList<Integer> list){

        System.out.println("*----------------------------------------------------------*");
        System.out.println("insert series: " + list.getClass().getSimpleName());

        System.out.print("Start \t time: \t");
        this.insertTest(this.startPositions(ARRAY_ELEMENTS_INSERT), list);

        System.out.print("Random \t time: \t");
        this.insertTest(this.randomPositions(ARRAY_ELEMENTS_INSERT, false), list);

        System.out.print("End \t time: \t");
        this.insertTest(this.endPositions(ARRAY_ELEMENTS_INSERT, false), list);

    }

    public void deleteTestSeries(ILinearList<Integer> list){

        System.out.println("*----------------------------------------------------------*");
        System.out.println("delete series: " + list.getClass().getSimpleName());

        System.out.print("Start \t time: ");
        this.deleteTest(this.startPositions(ARRAY_ELEMENTS_DELETE), list);

        System.out.print("Random \t time: ");
        this.deleteTest(this.randomPositions(ARRAY_ELEMENTS_DELETE, true), list);

        System.out.print("End \t time: ");
        this.deleteTest(this.endPositions(ARRAY_ELEMENTS_DELETE, true), list);

    }

    private void insertTest(int[] positions, ILinearList<Integer> list){

        for(int index = 0; index < RUNS; index++){

            list.clean();

            long start = System.currentTimeMillis();

            for(int i = 0; i < ARRAY_ELEMENTS_INSERT; i++){
                list.insert(positions[i], this.getRandomInteger());
            }

            long end = System.currentTimeMillis();

            System.out.print((end - start) + "\t");

        }

        System.out.println();

    }

    private void deleteTest(int [] positions, ILinearList<Integer> list){

        for(int index = 0; index < RUNS; index++){

            list.clean();

            // 1. Liste befüllen von hinten (Geht am schnellsten bei beiden)
            for(int i = 0; i < ARRAY_ELEMENTS_DELETE; i++){
                list.insert(i, this.getRandomInteger());
            }

            long start = System.currentTimeMillis();

            for(int i = 0; i < ELEMENT_DELETE_SIZE; i++){
                list.delete(positions[i]);
            }

            long end = System.currentTimeMillis();

            System.out.print((end - start) + "\t");


        }

        System.out.println();

    }


    private int[] startPositions(int size){
        return new int[size];
    }

    /**
     * falls reverse false random bound aufsteigend
     * falls reverse true random bound absteigend
     * @param size
     * @param reverse
     * @return
     */
    private int[] randomPositions(int size, boolean reverse){
        int[] result = new int[size];

        for(int i = 0; i < size; i++){

            if(reverse) {
                result[i] = new Random().nextInt((size)-i);
            } else {
                result[i] = new Random().nextInt(i+1);
            }
        }
        return result;
    }

    /**
     * reverse false -> 0 1 2 3 4 5
     * reverse true -> 5 4 3 2 1 0
     * @param size
     * @param reverse
     * @return
     */
    private int[] endPositions(int size, boolean reverse){

        int[] result = new int[size];

        for(int i = 0; i < size; i++){
            if(reverse){
                result[i] = (size-1)-i;
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

        LinearListPerformanceTestOld performenceTest = new LinearListPerformanceTestOld();

        performenceTest.insertTestSeries(new ArrayBasedList<Integer>());
        performenceTest.insertTestSeries(new DoubleLinkedList<Integer>());

        performenceTest.deleteTestSeries(new ArrayBasedList<Integer>());
        performenceTest.deleteTestSeries(new DoubleLinkedList<Integer>());

    }


}