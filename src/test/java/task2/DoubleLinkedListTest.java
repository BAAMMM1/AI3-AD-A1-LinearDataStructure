package task2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedListTest {

    @Test
    public void insertTestOrig(){
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        list.insert(0, new Integer(1));

        assertEquals(1, list.size());
        System.out.println(list.toString());

        list.insert(1, new Integer(2));

        assertEquals(2, list.size());
        System.out.println(list.toString());

        list.insert(2, new Integer(3));
        System.out.println(list.toString());
        list.insert(3, new Integer(4));
        System.out.println(list.toString());
        list.insert(4, new Integer(5));

        assertEquals(5, list.size());
        System.out.println(list.toString());


        list.insert(2, new Integer(9));

        assertEquals(6, list.size());

        System.out.println(list.toString());

    }


    @Test
    public void insertTest(){

        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        list.insert(0, new Integer(0));
        System.out.println(list.toString());

        list.insert(0, new Integer(1));
        System.out.println(list.toString());

        list.insert(2, new Integer(2));
        System.out.println(list.toString());

        list.insert(0, new Integer(3));
        System.out.println(list.toString());

        list.insert(2, new Integer(4));
        System.out.println(list.toString());

    }

}