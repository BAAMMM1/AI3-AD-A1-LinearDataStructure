package task2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedListTest {

    @Test
    public void insertTest(){

        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        list.insert(0, new Integer(1));
        System.out.println(list.toString());

        list.insert(0, new Integer(2));
        System.out.println(list.toString());

        list.insert(1, new Integer(3));
        System.out.println(list.toString());
    }

}