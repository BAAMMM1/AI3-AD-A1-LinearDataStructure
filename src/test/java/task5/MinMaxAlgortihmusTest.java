package task5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Chris on 12.04.2018
 */
public class MinMaxAlgortihmusTest {

    private static final int SIZE = 100;

    private static final int SMALLEST_VALUE_POSITVE = 0;
    private static final int BIGGEST_VALUE_POSITIVE = SMALLEST_VALUE_POSITVE + SIZE - 1;

    private static final int SMALLEST_VALUE_NEGATIVE = -100;
    private static final int BIGGEST_VALUE_NEGATIVE = SMALLEST_VALUE_NEGATIVE + SIZE - 1;

    private static final int MAX_COMPARISONS = 2 * SIZE;

    private static final int BOUND_RANDOM = 9999;

    private int[] sortedAscendingPositive;
    private int[] sortedAscendingNegative;
    private int[] sortedDescendingPositive;
    private int[] sortedDescendingNegative;
    private int[] sortedSamePositive;
    private int[] sortedSameNegative;
    private int[] sortedRandomPositiveNegative;

    private int randomMax;
    private int randomMin;


    @Before
    public void setup() {

        this.sortedAscendingPositive = new int[SIZE];
        this.sortedAscendingNegative = new int[SIZE];

        this.sortedDescendingPositive = new int[SIZE];
        this.sortedDescendingNegative = new int[SIZE];

        this.sortedSamePositive = new int[SIZE];
        this.sortedSameNegative = new int[SIZE];

        this.sortedRandomPositiveNegative = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            this.sortedAscendingPositive[i] = SMALLEST_VALUE_POSITVE + i;
            this.sortedAscendingNegative[i] = SMALLEST_VALUE_NEGATIVE + i;

            this.sortedDescendingPositive[i] = BIGGEST_VALUE_POSITIVE - i;
            this.sortedDescendingNegative[i] = BIGGEST_VALUE_NEGATIVE - i;

            this.sortedSamePositive[i] = SIZE;
            this.sortedSameNegative[i] = -SIZE;

            this.sortedRandomPositiveNegative[i] = this.getRandomInt(i);
        }

        this.randomMax = Arrays.stream(this.sortedRandomPositiveNegative).max().getAsInt();
        this.randomMin = Arrays.stream(this.sortedRandomPositiveNegative).min().getAsInt();

    }

    private int getRandomInt(int i) {

        if(i % 2 == 0){
            return -new Random().nextInt(BOUND_RANDOM);
        } else {
            return new Random().nextInt(BOUND_RANDOM);
        }

    }

    @Test
    public void computeAscendingPositive() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedAscendingPositive);

        assertEquals(SMALLEST_VALUE_POSITVE, algo.getMin());
        assertEquals(BIGGEST_VALUE_POSITIVE, algo.getMax());
        assertTrue((algo.getComparisons() < MAX_COMPARISONS));

    }

    @Test
    public void computeAscendingNegative() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedAscendingNegative);

        assertEquals(SMALLEST_VALUE_NEGATIVE, algo.getMin());
        assertEquals(BIGGEST_VALUE_NEGATIVE, algo.getMax());
        assertTrue((algo.getComparisons() < MAX_COMPARISONS));

    }

    @Test
    public void computeDescendingPositive() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedDescendingPositive);

        assertEquals(SMALLEST_VALUE_POSITVE, algo.getMin());
        assertEquals(BIGGEST_VALUE_POSITIVE, algo.getMax());
        assertTrue((algo.getComparisons() < MAX_COMPARISONS));

    }

    @Test
    public void computeDescendingNegative() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedDescendingNegative);

        assertEquals(SMALLEST_VALUE_NEGATIVE, algo.getMin());
        assertEquals(BIGGEST_VALUE_NEGATIVE, algo.getMax());
        assertTrue((algo.getComparisons() < MAX_COMPARISONS));

    }

    @Test
    public void computeSamePositive() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedSamePositive);

        assertEquals(SIZE, algo.getMin());
        assertEquals(SIZE, algo.getMax());
        assertTrue(algo.getComparisons() < MAX_COMPARISONS);

    }

    @Test
    public void computeSameNegative() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedSameNegative);

        assertEquals(-SIZE, algo.getMin());
        assertEquals(-SIZE, algo.getMax());
        assertTrue(algo.getComparisons() < MAX_COMPARISONS);

    }

    @Test
    public void computeRandomPositiveNegative() {

        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(this.sortedRandomPositiveNegative);

        assertEquals(this.randomMin, algo.getMin());
        assertEquals(this.randomMax, algo.getMax());
        assertTrue(algo.getComparisons() < MAX_COMPARISONS);

    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNull(){
        MinMaxAlgortihmus algo = new MinMaxAlgortihmus(null);
    }


}