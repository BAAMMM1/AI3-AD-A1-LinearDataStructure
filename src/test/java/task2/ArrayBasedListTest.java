package task2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris on 09.04.2018
 */
public class ArrayBasedListTest {

    @Test
    public void inserTest(){
        ArrayBasedList<Integer> liste = new ArrayBasedList<Integer>();

        liste.insert(0, new Integer(1));

        assertEquals(1, liste.size());
        System.out.println(liste.toString());

        liste.insert(1, new Integer(2));

        assertEquals(2, liste.size());
        System.out.println(liste.toString());

        liste.insert(2, new Integer(3));
        System.out.println(liste.toString());
        liste.insert(3, new Integer(4));
        System.out.println(liste.toString());
        liste.insert(4, new Integer(5));

        assertEquals(5, liste.size());
        System.out.println(liste.toString());


        liste.insert(2, new Integer(9));

        assertEquals(6, liste.size());

        System.out.println(liste.toString());

    }

}