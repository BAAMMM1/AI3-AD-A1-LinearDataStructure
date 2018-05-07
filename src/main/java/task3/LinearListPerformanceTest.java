package task3;

import task1.ILinearList;
import task2.ArrayBasedList;
import task2.DoubleLinkedList;

import java.util.Random;

/**
 * Diese Klasse setzt einen Performancetest für Implementationen von ILinearList<T> um.
 *
 * @author Chris on 09.04.2018
 */
public class LinearListPerformanceTest {

    /**
     * Anzahl der Durchläufe.
     */
    private static final int RUNS = 10;

    /**
     * Anzahl an Elementen die beim InsertTest eingefügt werden.
     */
    private static final int ARRAY_ELEMENTS_TO_INSERT = 20000;

    /**
     * Anzahl an Elementen die in der Liste für die DeleteTest sind.
     */
    private static final int INIT_ARRAY_SIZE_FOR_DELETE = 20000;

    /**
     * Anzahl an Elementen die beim DeleteTest gelsöcht werden.
     */
    private static final int ELEMENT_DELETE_SIZE = 1000;

    /**
     * Randomschranke für die Integer-Werte
     */
    private static final int RANDOM_BOUND = 999999999;

    /**
     * Testbezeichnung
     */
    private static final String[] QUALIFIER = new String[]{"Start", "Random", "End"};

    /**
     * Positionen für die InsertSerie
     */
    private static final int[][] POSITIONS_INSERT = new int[][]{getStartPositions(), getRandomPositions(false), getEndPositions(false)};

    /**
     * Positionen für die DeleteSerie
     */
    private static final int[][] POSITIONS_DELETE = new int[][]{getStartPositions(), getRandomPositions(true), getEndPositions(true)};


    /**
     * Diese Methode startet den Vergleich der Listen. Dafür wird eine InsertSerie und eine DeleteSerie ausgeführt.
     * Die Liste an Position 0 stellt die Referenzliste da.
     * @param lists nicht null
     */
    public void comparison(ILinearList<Integer>... lists) {

        if(lists == null) throw new IllegalArgumentException();

        System.out.println("Refrenzliste: " + lists[0].getClass().getSimpleName());

        this.insertSeries(QUALIFIER, POSITIONS_INSERT, lists);

        this.deleteSeries(QUALIFIER, POSITIONS_DELETE, lists);

    }


    /**
     * Führt für die übergebenen Listen und den Positionen in der Konstante POSITIONS_INSERT die InsertTests durch.
     *
     * @param qualifier muss gleiche Anzahl wie posInsert haben
     * @param posInsert muss gleiche Anzahl wie Qualifier haben
     * @param lists darf nicht null sein
     */
    private void insertSeries(String[] qualifier, int[][] posInsert, ILinearList<Integer>... lists) {

        if (QUALIFIER.length != POSITIONS_INSERT.length) throw new IllegalArgumentException("must be the same size");
        if (lists == null) throw new IllegalArgumentException();

        // ErgebnisArray für die gesammelten Durchschnitte der Messungen für den jeweiligen Test für die jeweilie Liste.
        long[] resultsAverageInsert = new long[lists.length * 3];

        System.out.println("*-------------------- insert series -------------------------------- average - compare ---------*");


        for (int i = 0; i < lists.length; i++) {
            // Führe Test für jede Liste durch

            System.out.println(lists[i].getClass().getSimpleName() + ":");

            for (int j = 0; j < posInsert.length; j++) {
                // Führe Test für die jeweiligen Positionen den InsertTest durch.

                System.out.printf("%-10s: ", qualifier[j]);
                resultsAverageInsert[i*3+j] = this.insertTest(posInsert[j], lists[i]);

                if(i == 0) {
                    // Ausgabe: Beim ersten Durchlauf handelt es sich um die Referenzliste (Position = 0)
                    System.out.printf("%5s |%8d |%8s |\n", "", resultsAverageInsert[i*3+j], "");
                } else {
                    // Ausgabe: Alle weiteren Durchläufe werden mit der Referenzliste verglichen.
                    System.out.printf("%5s |%8d |%+8d |\n", "", resultsAverageInsert[i*3+j], (resultsAverageInsert[i*3+j]-resultsAverageInsert[j]));

                }
            }

            System.out.println("*-----------------------------------------------------------------------------------------------*");

        }

        System.out.println();

    }

    /**
     * Führt für die übergebenen Listen und den Positionen in der Konstante POSITIONS_INSERT die DeleteTests durch.
     *
     * @param qualifier muss gleiche Anzahl wie posDelete haben
     * @param posDelete muss gleiche Anzahl wie qualifier haben
     * @param lists darf nicht null sein
     */
    private void deleteSeries(String[] qualifier, int[][] posDelete, ILinearList<Integer>... lists) {

        if (QUALIFIER.length != POSITIONS_DELETE.length) throw new IllegalArgumentException("must be the same size");
        if (lists == null) throw new IllegalArgumentException();

        // ErgebnisArray für die gesammelten Durchschnitte der Messungen für den jeweiligen Test für die jeweilie Liste.
        long[] resultsAverageDelete = new long[posDelete.length * 2];

        System.out.println("*-------------------- delete series -------------------------------- average - compare ---------*");

        for (int i = 0; i < lists.length; i++) {
            // Führe Test für jede Liste durch
            System.out.println(lists[i].getClass().getSimpleName() + ":");


            for (int j = 0; j < posDelete.length; j++) {
                // Führe Test für die jeweiligen Positionen DeleteTest durch.

                System.out.printf("%-10s: ",qualifier[j]);

                resultsAverageDelete[i*3+j] = this.deleteTest(posDelete[j], lists[i]);

                if(i == 0) {
                    // Ausgabe: Beim ersten Durchlauf handelt es sich um die Referenzliste (Position = 0)
                    System.out.printf("%5s |%8d |%8s |\n", "", resultsAverageDelete[i*3+j], "");
                } else {
                    // Ausgabe: Alle weiteren Durchläufe werden mit der Referenzliste verglichen.
                    System.out.printf("%5s |%8d |%+8d |\n", "", resultsAverageDelete[i*3+j], (resultsAverageDelete[i*3+j]-resultsAverageDelete[j]));

                }
            }

            System.out.println("*-----------------------------------------------------------------------------------------------*\n");

        }

    }


    /**
     * Diese Methode leert die übergebene Liste und fügt an den Positionen im Positionsarray random Integer ein.
     * Dieser Vorgang wird RUNS mal durchgeführt und anschließend wird der Mittelwert für die Durchläufe ermittelt
     * und übergeben.
     *
     * @param positions nicht null
     * @param list nicht null
     * @return Mittelwert der Messungen
     */
    private long insertTest(int[] positions, ILinearList<Integer> list) {

        if (positions == null) throw new IllegalArgumentException();
        if (list == null) throw new IllegalArgumentException();

        // ErgbnisArray für die jeweiligen Durchläufe.
        long[] results = new long[RUNS];

        for (int index = 0; index < RUNS; index++) {
            // Führe den Test für die jeweiligen Positionen RUNS mal durch

            list.clean();

            // Startzeitpunkt messen
            long start = System.currentTimeMillis();

            // Einfügen
            for (int i = 0; i < ARRAY_ELEMENTS_TO_INSERT; i++) {
                list.insert(positions[i], this.getRandomInteger());
            }

            // Endzeitpunkt
            long end = System.currentTimeMillis();

            System.out.printf("%5s", (end - start));

            results[index] = end - start;

        }

        // Durchschnitt der Messungen berechnen
        long temp = 0;

        for (long result : results) {
            temp += result;

        }

            // Rückgabe der Durchschnittsdauer
        return temp / RUNS;

    }

    /**
     * Diese Methode leert die übergebene Liste, fügt INIT_ARRAY_SIZE_FOR_DELETE Elemente der Liste hinzu
     * und löscht an den Positionen des Positionsarray die Elemente.
     * Dieser Vorgang wird RUNS mal durchgeführt und anschließend wird der MIttelwert für die Durchläufe ermittelt
     * und übergeben.
     *
     * @param positions nicht null
     * @param list nicht null
     * @return Mittelwert der Messungen
     */
    private long deleteTest(int[] positions, ILinearList<Integer> list) {

        if (positions == null) throw new IllegalArgumentException();
        if (list == null) throw new IllegalArgumentException();

        // ErgbnisArray für die jeweiligen Durchläufe.
        long[] results = new long[RUNS];

        for (int index = 0; index < RUNS; index++) {
            // Führe den Test für die jeweiligen Positionen RUNS mal durch

            list.clean();

            // 1. Liste befüllen von hinten (Geht am schnellsten bei beiden)
            for (int i = 0; i < INIT_ARRAY_SIZE_FOR_DELETE; i++) {
                list.insert(i, this.getRandomInteger());
            }

            // Startzeitpunkt messen
            long start = System.currentTimeMillis();

            // löschen
            for (int i = 0; i < ELEMENT_DELETE_SIZE; i++) {
                list.delete(positions[i]);
            }

            // Endzeitpunkt
            long end = System.currentTimeMillis();

            System.out.printf("%5s", (end - start));

            results[index] = end - start;

        }

        // Durchschnitt der Messungen berechnen
        long temp = 0;

        for (long result : results) {
            temp += result;

        }

        // Rückgabe der Durchschnittsdauer
        return temp / RUNS;

    }


    /**
     * Gibt ein Array mit StartPositionen zurück -> 0, 0 , 0, 0 ....
     * @return StartPositionen
     */
    private static int[] getStartPositions() {
        return new int[ARRAY_ELEMENTS_TO_INSERT];
    }

    /**
     * Gibt ein Array mit RandomPositionen zurück.
     * Die jeweiligen RandomPosition ist dabei beschränkt durch die größe der Liste.
     *
     * falls reverse false random bound aufsteigend - Mögliche Randoms je Durchlauf {0}, {0,1}, {0,1,2}, {0,1,2,3} .... (insert)
     * falls reverse true random bound absteigend - Mögliche Randoms je Durchlauf {MaxSize, Max-1 ....} , MaxSize wird je Durchlauf um 1 Kleiner (delete(
     *
     * @param reverse
     * @return RandomPositionen
     */
    private static int[] getRandomPositions(boolean reverse) {
        int[] result = new int[ARRAY_ELEMENTS_TO_INSERT];

        for (int i = 0; i < ARRAY_ELEMENTS_TO_INSERT; i++) {

            if (reverse) {
                result[i] = new Random().nextInt((ARRAY_ELEMENTS_TO_INSERT) - i);
            } else {
                result[i] = new Random().nextInt(i + 1);
            }
        }
        return result;
    }

    /**
     * Gibt ein Array mit Endpositionen zurück
     * Insert: reverse false -> 0 1 2 3 4 5 (insert)
     * Delete: reverse true -> 5 4 3 2 1 0 (delete)
     *
     * @param reverse
     * @return Endpositionen
     */
    private static int[] getEndPositions(boolean reverse) {

        int[] result = new int[ARRAY_ELEMENTS_TO_INSERT];

        for (int i = 0; i < ARRAY_ELEMENTS_TO_INSERT; i++) {
            if (reverse) {
                result[i] = (ARRAY_ELEMENTS_TO_INSERT - 1) - i;
            } else {
                result[i] = i;
            }
        }
        return result;
    }


    /**
     * Diese Mehtode erstellt ein zufälliges Integer-Objekt
     * @return
     */
    private Integer getRandomInteger() {
        Random random = new Random();
        return new Integer(random.nextInt(RANDOM_BOUND));
    }

    public static void main(String[] args) {

        LinearListPerformanceTest pTest = new LinearListPerformanceTest();

        pTest.comparison(new ArrayBasedList<Integer>(), new DoubleLinkedList<Integer>());

        pTest.comparison(new DoubleLinkedList<Integer>(), new ArrayBasedList<Integer>());
    }


}
