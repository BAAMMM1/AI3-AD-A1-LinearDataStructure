package task2;

import org.junit.Before;
import org.junit.Test;
import task1.ILinearList;

import java.util.Random;

import static org.junit.Assert.*;

// Grenzwerte, Testfälle (Positivtest), Unzulässige Eingaben (Negativtest)
// Testfälle - Vorbereitet, Random
// Zeitschranken
//        1.	Grenzwerte      sind dies nicht schon Positiv und Negativtests?
//                              Positiv, was min und max geht
//                              Negativ, was dann nicht mehr geht
//        2.	Testfälle (Positivtest) - BlackBox
//              a.	Vorbereitet
//              b.	Random
//              c.	Code-Abdeckung      Testen des Inhaltes jeder If Abfrage(if, elseif, elseif, else) - WhiteBox
//              d.	Pfad-Abdeckung      verkettete if ablaufen, was ist default weg/Ergebnis           - WhiteBox
//        3.	Unzulässige Eingaben (Negativtest) - WhiteBox
//              a.	Fluchtwerte
//              b.	Default-Werte
//              c.	Exception
//              d.	Abbrechen des Programms (Gescheiterte Nachbedingung (Assertion))
//              e.	Undefiniertes Verhalten
//        4.	Zeitschranken


/**
 * @author Shadai on 10.04.2018
 */
public class ArrayBasedListTest {
    private ILinearList iLinearListZero;
    private ILinearList iLinearListRandomForManipulateFilledWithInt;
    private ILinearList iLinearListRandomForCompareFilledWithInt;
    private ILinearList iLinearListFilledWithDouble;
    private int randomIntNumber;
    private int otherRandomIntNumber;

    private final static int MAX_RANDOMNUMBER = 11;
    private final static String STRING_TYPE_INSTEAD_OF_INT = "HelloWorld";
    private final static double DOUBLE_TYPE_INSTEAD_OF_INT = 12.34;

    @Before
    public void setup(){
        // 2 Integer Zufallszahlen
        randomIntNumber = new Random().nextInt(MAX_RANDOMNUMBER);
        otherRandomIntNumber = new Random().nextInt(MAX_RANDOMNUMBER);


        // Leere Liste
        iLinearListZero = new ArrayBasedList<Integer>();

        // 2 gleiche zufällig gefüllte Listen mit Integer Werten
        iLinearListRandomForManipulateFilledWithInt = new ArrayBasedList<Integer>();
        iLinearListRandomForCompareFilledWithInt = new ArrayBasedList<Integer>();
        iLinearListFilledWithDouble = new ArrayBasedList<Double>();
        for (int i = 0; i < randomIntNumber; i++){
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
                randomIntNumber,
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
                randomIntNumber,
                iLinearListRandomForManipulateFilledWithInt.retrieve(0));
    }
    @Test
    public void insertOnRandomPositionARandomNumberOfRandomFilledList() {
       // zufällige Zahl an zufälliger Position soll via insert hinzugefügt werden
        iLinearListRandomForManipulateFilledWithInt.insert(randomIntNumber, otherRandomIntNumber);

        // Vergleichsliste wird erstellt
        iLinearListRandomForCompareFilledWithInt.clean();
        // 1. part bis random
        for (int i = 0; i < randomIntNumber -1; i++)
        { iLinearListRandomForCompareFilledWithInt.insert(i, iLinearListRandomForManipulateFilledWithInt.retrieve(i));}
        // 2. Part, gleiche zufällige Zahl an gleicher zufäligen Position
        iLinearListRandomForCompareFilledWithInt.insert(iLinearListRandomForCompareFilledWithInt.size(), otherRandomIntNumber);
        // 3. Part Rest der Liste
        for (int i = iLinearListRandomForCompareFilledWithInt.size(); i< iLinearListRandomForManipulateFilledWithInt.size(); i++)
       iLinearListRandomForCompareFilledWithInt.insert(i, iLinearListRandomForManipulateFilledWithInt.retrieve(i));

        // oder via wiederverwertung von geschriebenen methoden

//        iLinearListRandomForCompareFilledWithInt.clean();
//        // 1. Part bis Random
//        iLinearListRandomForCompareFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt.extract(0,randomIntNumber-1));
//        // 2. Part, gleiche zufällige Zahl an gleicher zufäligen Position
//        iLinearListRandomForCompareFilledWithInt.insert(randomIntNumber, otherRandomIntNumber);
//        // 3. Part Rest der Liste   (hier muss randomnumber +1, da wir das zufällige Element ja schon hinzugefügt haben)
//        // Vorbedinung von extract wird hier verletzt, warum?!? von manipulierter Liste hinter der hinzugefügten
//        // zufallszahl an randomposition bis letzer posi -> size-1, und das verstößt gegen beide vorbedinungen,
//        // das hinter random posi >= 0 und < size, und danach verstoß gegen letzte posi -> letzte posi > randomposi+1 und letzte posi < size
//        // sind also von der Logik her alles berücksichtigt und wirft trotzdem Illegal Argument
//        iLinearListRandomForCompareFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt.extract(randomIntNumber+1, iLinearListRandomForManipulateFilledWithInt.size()-1));

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

    // Negativtest
    @Test(expected = IllegalArgumentException.class)
    public void insertWrongTypeInListOfOtherTypes(){
        // Try to insert another type of element as list is filled of
        iLinearListRandomForManipulateFilledWithInt.insert(randomIntNumber,STRING_TYPE_INSTEAD_OF_INT);
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
    public void concatRandomListToRandomListLength(){
        //pööse noch nicht machen, weil noch nicht abgefangen wird
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListRandomForCompareFilledWithInt);
        assertEquals("Concat two RandomLists and compare Length",
                iLinearListRandomForCompareFilledWithInt.size()*2,
                iLinearListRandomForManipulateFilledWithInt.size());
    }


// Läßt man den Test drin, dann hängt er sich an den Precondition von retrieve wieder auf, nimmt man die Precondition weg,
// dann ist der Test erfolgreich aber die Negativtests schlägt fehl -> vor pos 0 und Posi weit hinter liste
// Irgendwie scheinen concat aktionen usw nicht sofort den wert size und co zu übernehmen
    @Test
    public void concatRandomListToRandomListContent(){
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListRandomForCompareFilledWithInt);
        // Vergleich vorderer Part mit hinterem, da beide gleich waren und aneinandergehangen wurden
        assertEquals("Concat two RandomLists and compare content at random position",
                iLinearListRandomForCompareFilledWithInt.retrieve(randomIntNumber),
                iLinearListRandomForManipulateFilledWithInt.retrieve(randomIntNumber + iLinearListRandomForCompareFilledWithInt.size()));
    }

    // Negativtest
    @Test(expected = IllegalArgumentException.class)
    public void concatAnythingElseThenListToList(){
        // Eine Liste mit Elementen an eine andere Liste mit anderen Elementen hängen
        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListFilledWithDouble);
    }

    //*****************************************************************************
    // Erst auskommentieren wenn umsetzung erfüllt, da sonst Out of Memory Test!!!
    //*****************************************************************************

//    @Test(expected = IllegalArgumentException.class)
//    public void concatSameObjectToEachOther{
//        // Try to concat same object to itself
//        iLinearListRandomForManipulateFilledWithInt.concat(iLinearListRandomForManipulateFilledWithInt);
//    };


    //Positivtest
    @Test
    public void extractFromFirstToRandomOfRandomFilledList() {
        iLinearListRandomForManipulateFilledWithInt.clean();
        for (int i = 0; i < randomIntNumber -1; i++) {
            iLinearListRandomForManipulateFilledWithInt.insert(i, iLinearListRandomForManipulateFilledWithInt.retrieve(i));
        }
        assertEquals("Extract from first position to random position at random filled list",
                iLinearListRandomForManipulateFilledWithInt, iLinearListRandomForCompareFilledWithInt.extract(0,randomIntNumber));

    }

    //Negativtest
    @Test(expected = IllegalArgumentException.class)
    public void extractOfZeroList(){
        // Try extracting of empty list
        iLinearListZero.extract(0,0);
    }

}



// insert-Tests
//@Test(expected = IllegalArgumentException.class)                                                                      1. Idee expected
//        aus insert, ideen für fehler abfangen (um zu sehen ob auch der richtige fehler geworfen wird)
//        try{                                                                                                          2. Idee tryCatch
//            iLinearListRandomForManipulateFilledWithInt.insert(0, null);
//        } catch (IllegalArgumentException e){
//
//            assertEquals(IllegalArgumentException.class, e.getClass());
//        }