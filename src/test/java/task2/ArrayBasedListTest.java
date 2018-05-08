package task2;

import org.junit.Before;
import org.junit.Test;
import task1.ILinearList;

import java.util.Random;

import static org.junit.Assert.*;

// Grenzwerte, Testfälle (Positivtest), Unzulässige Eingaben (Negativtest)
// Testfälle - Vorbereitet, Random
// Zeitschranken
//        1.	Testfälle (Positivtest) -
//              a. Grenzwerte - Positiv, was min und max geht - BlackBox
//              a.	Vorbereitet - BlackBox
//              b.	Random - BlackBox
//              c.	Code-Abdeckung      Testen des Inhaltes jeder If Abfrage(if, elseif, elseif, else) - WhiteBox
//              d.	Pfad-Abdeckung      verkettete if ablaufen, was ist default weg/Ergebnis           - WhiteBox
//        3.	Unzulässige Eingaben (Negativtest) - WhiteBox
//              a.	Fluchtwerte - WhiteBox
//              b.	Default-Werte - WhiteBox
//              c.	Exception - Negativ, was dann nicht mehr geht - WhiteBox
//              d.	Abbrechen des Programms (Gescheiterte Nachbedingung (Assertion)) - WhiteBox
//              e.	Undefiniertes Verhalten
//        4.	Zeitschranken


/**
 * @author Shadai on 10.04.2018
 */
public class ArrayBasedListTest {
    private ILinearList iLinearListZero;
    private ILinearList<Integer> iLinearListRandomForManipulateFilledWithInt;
    private ILinearList<Integer> iLinearListRandomForCompareFilledWithInt;
    private ILinearList<Double> iLinearListFilledWithDouble;
    private Integer randomIntNumber;
    private Integer otherRandomIntNumber;

    private final static Integer MAX_RANDOMNUMBER = 11;
    private final static String STRING_TYPE_INSTEAD_OF_INT = "HelloWorld";
    private final static Double DOUBLE_TYPE_INSTEAD_OF_INT = 12.34;

    @Before
    public void setup(){
        // 2 Integer Zufallszahlen
        randomIntNumber = new Random().nextInt(MAX_RANDOMNUMBER)+1;
        otherRandomIntNumber = new Random().nextInt(MAX_RANDOMNUMBER)+1;


        // Leere Liste
        iLinearListZero = new ArrayBasedList<Integer>();

        // 2 gleiche zufällig gefüllte Listen mit Integer Werten
        iLinearListRandomForManipulateFilledWithInt = new ArrayBasedList<Integer>();
        iLinearListRandomForCompareFilledWithInt = new ArrayBasedList<Integer>();
        iLinearListFilledWithDouble = new ArrayBasedList<Double>();
        for (int i = 0; i < randomIntNumber+3; i++){
            int k = new Random().nextInt(MAX_RANDOMNUMBER);
            iLinearListRandomForManipulateFilledWithInt.insert(i, k);
            iLinearListRandomForCompareFilledWithInt.insert(i, k);
            iLinearListFilledWithDouble.insert(i, (double)k);
        }

    }



    // size-Tests
    // 1. Positivtests
    @Test
    public void sizeZeroList() {
        assertEquals("Size of empty list",
                0,
                iLinearListZero.size());
    }

    @Test
    public void sizeRandomFilledList() {
        assertEquals("Size of random hugh list",
                randomIntNumber.intValue()+3,
                iLinearListRandomForManipulateFilledWithInt.size());
    }



    // insert-Tests
    // 1. Positivtests
    @Test
    public void insertRandomNumberAtEmptyList(){
        iLinearListZero.insert(0, randomIntNumber);
        assertEquals("Insert random number on first position of empty List",
                randomIntNumber,
                iLinearListZero.retrieve(0));
    }

    @Test
    public void insertRandomNumberAtFirstPositionOfRandomFilledList(){
        iLinearListRandomForManipulateFilledWithInt.insert(0, randomIntNumber);
        assertEquals("Insert random number at first Postion of random filled List",
                randomIntNumber.intValue(),
                iLinearListRandomForManipulateFilledWithInt.retrieve(0).intValue());
    }

    @Test
    public void insertOnRandomPositionARandomNumberOfRandomFilledList() {
//        System.out.println("Liste vor Manipulation: "+iLinearListRandomForManipulateFilledWithInt.toString());
        iLinearListRandomForCompareFilledWithInt.clean();
        randomIntNumber = new Random().nextInt(iLinearListRandomForManipulateFilledWithInt.size()-1)+1 ;

//        System.out.println("Zufällige Position randomInt: " + randomIntNumber);

        // 1. Part 1 bis Position 1 vor RandomNumber
        iLinearListRandomForCompareFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt.extract(0, randomIntNumber-1));
        // 2. Part, eine andere zufällige Zahl an die Position RandomNumber
        iLinearListRandomForCompareFilledWithInt.insert(randomIntNumber, otherRandomIntNumber);
//         System.out.println("Zufälliger eingetragener Wert otherrandom: "+otherRandomIntNumber);
        // 3. Part von der alten List ab RandomNumber aus bis zum Ende
        iLinearListRandomForCompareFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt.extract(randomIntNumber, iLinearListRandomForManipulateFilledWithInt.size()-1));

//         System.out.println("Neue Manipulierte Liste: " + iLinearListRandomForCompareFilledWithInt.toString());
        iLinearListRandomForManipulateFilledWithInt.insert(randomIntNumber, otherRandomIntNumber);
        assertEquals("Insert random number at random position of random filled List",
                iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt);
    }

    @Test
    public void insertRandomNumberDirectBehindRandomFilledList(){
        iLinearListRandomForManipulateFilledWithInt.insert(iLinearListRandomForManipulateFilledWithInt.size(), randomIntNumber);
        assertEquals("Insert random number direct behind random filled List",
                randomIntNumber,
                iLinearListRandomForManipulateFilledWithInt.retrieve(iLinearListRandomForManipulateFilledWithInt.size()-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertBelowFirstPositionOfRandomFilledList(){
        // Try to insert below first position
        iLinearListRandomForManipulateFilledWithInt.insert(-1*(randomIntNumber +1), otherRandomIntNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertFarBehindLastPositionOfRandomFilledList(){
        // Try to insert far behind last position
        iLinearListRandomForManipulateFilledWithInt.insert(iLinearListRandomForManipulateFilledWithInt.size()+MAX_RANDOMNUMBER, randomIntNumber);
    }



    // delete-Tests
    // 1. Positivtest
    @Test
    public void deleteEmptyList() {
        iLinearListZero.clean();
        assertEquals("Deleting Empty List",
                0,
                iLinearListZero.size());
    }

    @Test
    public void deleteRandomSizedList() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        assertEquals("Deleting Randomsized List",
                0,
                iLinearListRandomForManipulateFilledWithInt.size());
    }



    // retrieve-Test
    //1. Positivtest
    @Test
    public void retrieveRandomNumberAtFirstPositionOfRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.insert(0, randomIntNumber);
        assertEquals("Retrieving Random Number at 1st Position of Random filled List",
                randomIntNumber,
                iLinearListRandomForManipulateFilledWithInt.retrieve(0));
    }

    @Test
    public void retrieveRandomNumberAtRandomPositionOfRandomFilledList() {
        // Letzte Position wäre -1, und nicht die erste Position, also -1 und das ganze +1 falls 0 via random rauskommen sollte
        randomIntNumber = new Random().nextInt(iLinearListRandomForManipulateFilledWithInt.size()-2) +1 ;
        iLinearListRandomForManipulateFilledWithInt.insert(randomIntNumber, otherRandomIntNumber);
        assertEquals("Retrieving Random Number at Randomposition of Random filled List",
                otherRandomIntNumber,
                iLinearListRandomForManipulateFilledWithInt.retrieve(randomIntNumber));
    }
    @Test

    public void retrieveRandomNumberAtLastPositionOfRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.insert(iLinearListRandomForManipulateFilledWithInt.size(), randomIntNumber);
        assertEquals("Retrieving Random Number at last Position of Random filled List",
                randomIntNumber,
                iLinearListRandomForManipulateFilledWithInt.retrieve(iLinearListRandomForManipulateFilledWithInt.size()-1));
    }

    // 2. Negativtest - Grenzfälle
    @Test(expected = IllegalArgumentException.class)
    public void retrieveFromPositionZeroOfEmptyList(){
        // Retrieving from Position 0 of empty List
        iLinearListZero.retrieve(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrieveFromPositionBelowZeroOfRandomFilledList(){
        // Retrieving from Position below 0 of Random filled List
        iLinearListRandomForManipulateFilledWithInt.retrieve(-1* randomIntNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void retrieveFromPositionBehindLastPositionOfRandomFilledList(){
        // Retrieving from Postion behind Last Position of Random filled List
        iLinearListRandomForManipulateFilledWithInt.retrieve(iLinearListRandomForManipulateFilledWithInt.size()+MAX_RANDOMNUMBER);
    }



    // clean-tests
    @Test
    public void cleanRandomList() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        assertEquals("Clean-Method testing on Random hugh List",
                0,
                iLinearListRandomForManipulateFilledWithInt.size());
    }

    @Test
    public void cleanZeroList() {
        iLinearListZero.clean();
        assertEquals("Clean-Method testing on empty List",
                0,
                iLinearListZero.size());
    }



    // 1. Positivtest
    @Test
    public void concatZeroListToZeroList() {
        iLinearListZero.concat(iLinearListZero);
        assertEquals("Concat empty list to empty list",
                0,
                iLinearListZero.size());
    }

    @Test
    public void concatRandomFilledListToZeroList() {
        iLinearListZero.concat(iLinearListRandomForManipulateFilledWithInt);
        assertEquals("Concat random filled list to empty list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListZero);
    }

    @Test
    public void concatZeroListToRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListZero);
        assertEquals("Concat empty list to random filled list",
                iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt);
    }


    @Test
    public void concatRandomListToRandomListContent(){
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListRandomForCompareFilledWithInt);
        // Vergleich vorderer Part mit hinterem, da beide gleich waren und aneinandergehangen wurden
        assertEquals("Compare of first part of concat two random list of same content", iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt.extract(0,iLinearListRandomForCompareFilledWithInt.size()-1));

        assertEquals("Compare of first part of concat two random list of same content", iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt.extract(iLinearListRandomForCompareFilledWithInt.size(),
                        iLinearListRandomForManipulateFilledWithInt.size()-1));
    }



    @Test
    public void concatSameObjectToEachOtherContent(){
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt);

        assertEquals("Compare of first part of contact same object to each other", iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt.extract(0,
                        iLinearListRandomForCompareFilledWithInt.size()-1));
        assertEquals("Compare of 2nd part of contact same object to each other", iLinearListRandomForCompareFilledWithInt,
                iLinearListRandomForManipulateFilledWithInt.extract(iLinearListRandomForCompareFilledWithInt.size(),
                        iLinearListRandomForManipulateFilledWithInt.size()-1));
    };


    //Positivtest
    @Test
    public void extractFromFirstPositionToFirstPosition() {

        iLinearListRandomForManipulateFilledWithInt.clean();
        iLinearListRandomForManipulateFilledWithInt.insert(0, iLinearListRandomForCompareFilledWithInt.retrieve(0));

        assertEquals("Extract from first position to first position at random filled list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(0,0));
    }

    @Test
    public void extractFromLastPositionToLastPosition() {

        iLinearListRandomForManipulateFilledWithInt.clean();
        iLinearListRandomForManipulateFilledWithInt.insert(0,
                iLinearListRandomForCompareFilledWithInt.retrieve(iLinearListRandomForCompareFilledWithInt.size()-1));
        assertEquals("Extract from last position to last position at random filled list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(iLinearListRandomForCompareFilledWithInt.size()-1,
                        iLinearListRandomForCompareFilledWithInt.size()-1));
    }

    @Test
    public void extractFromFirstPositionToLastPosition() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        for (int i=0; i < iLinearListRandomForCompareFilledWithInt.size()-1; i++){
            iLinearListRandomForManipulateFilledWithInt.insert(i, iLinearListRandomForCompareFilledWithInt.retrieve(i));
        }
        assertEquals("Extract from first position to last position at random filled list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(0,
                        iLinearListRandomForManipulateFilledWithInt.size()-1));
    }

    @Test
    public void extractFromFirstToRandomOfRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        randomIntNumber = new Random().nextInt(iLinearListRandomForCompareFilledWithInt.size()-2)+1;
        for (int i = 0; i < randomIntNumber ; i++) {
            iLinearListRandomForManipulateFilledWithInt.insert(i, iLinearListRandomForCompareFilledWithInt.retrieve(i));
        }
        assertEquals("Extract from first position to random position at random filled list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(0,randomIntNumber-1));
    }

    @Test
    public void extractFromRandomPositionToLastPositionOfRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        randomIntNumber = new Random().nextInt(iLinearListRandomForCompareFilledWithInt.size()-2)+1;
        for (int i = randomIntNumber; i < iLinearListRandomForCompareFilledWithInt.size() ; i++) {
            iLinearListRandomForManipulateFilledWithInt.insert(i-randomIntNumber, iLinearListRandomForCompareFilledWithInt.retrieve(i));
        }
        assertEquals("Extract from random position to last position at random filled list",
                iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(randomIntNumber,iLinearListRandomForCompareFilledWithInt.size()-1));
    }
    // von random zu random

    @Test
    public void extractFromRandomPositionToRandomPosition(){
        iLinearListRandomForManipulateFilledWithInt.clean();
        randomIntNumber = new Random().nextInt(iLinearListRandomForCompareFilledWithInt.size()-2)+1;
        otherRandomIntNumber = new Random().nextInt(iLinearListRandomForCompareFilledWithInt.size()-2)+1;
        int lowerPosition = (randomIntNumber<otherRandomIntNumber)? (randomIntNumber) : (otherRandomIntNumber);
        int biggerPosition = (randomIntNumber<otherRandomIntNumber)? (otherRandomIntNumber) : (randomIntNumber);

        for (int i = lowerPosition; i <= biggerPosition; i++){
            iLinearListRandomForManipulateFilledWithInt.insert(i-lowerPosition, iLinearListRandomForCompareFilledWithInt.retrieve(i));
        }

        assertEquals("Extract from two random Positions at random filled list", iLinearListRandomForManipulateFilledWithInt,
                iLinearListRandomForCompareFilledWithInt.extract(lowerPosition,biggerPosition));
    }

    //Negativtest
    @Test(expected = IllegalArgumentException.class)
    public void extractOfZeroList(){
        // Try extracting of empty list
        iLinearListZero.extract(0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void extractFromLastPositionToFirstPosition() {
        // Try extracting From Behind to Front
        iLinearListRandomForManipulateFilledWithInt.extract(iLinearListRandomForManipulateFilledWithInt.size(), 0);
    }
}