package task2;

import org.junit.Before;
import org.junit.Test;
import task1.ILinearList;

import java.util.Random;

import static org.junit.Assert.*;

// Grenzwerte, Testfälle (Positivtest), Unzulässige Eingaben (Negativtest)
// Testfälle - Vorbereitet, Random
// Zeitschranken
//        1.	Grenzwerte
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
    private ILinearList iLinearListRandom;
    private int randomNumber;

    @Before
    public void setup(){

        iLinearListZero = new ArrayBasedList<Integer>();

        iLinearListRandom = new ArrayBasedList<Integer>();
        randomNumber = new Random().nextInt(11);
        for (int i=0; i < randomNumber; i++){
            iLinearListRandom.insert(i, new Integer (randomNumber*10));
        }
    }



    // size-Tests
    @Test
    public void sizeZeroList() {
        assertEquals("Size of empty list",0,iLinearListZero.size());
    }

    @Test
    public void sizeRandomList() {
        assertEquals("Size of random hugh list",randomNumber,iLinearListRandom.size());
    }



    // insert-Tests
    @Test
    public void insert() {
    }



    // delete-Tests
    @Test
    public void delete() {
    }



    // retrieve-Test
    @Test
    public void retrieve() {
    }



    // clean-tests
    @Test
    public void cleanRandomList() {
        iLinearListRandom.clean();
        assertEquals("Clean-Method testing on Random hugh List",0,iLinearListRandom.size());
    }



    @Test
    public void cleanZeroList() {
        iLinearListZero.clean();
        assertEquals("Clean-Method testing on empty List",0,iLinearListZero.size());
    }



    @Test
    public void concat() {
    }



    @Test
    public void extract() {
    }
}