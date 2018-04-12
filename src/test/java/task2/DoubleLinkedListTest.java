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

        list.insert(3, new Integer(4));
        System.out.println(list.toString());

        //System.out.println("---->: " + list.retrieve(1));

        //System.out.println(list.extract(1,2));

        System.out.println(list.retrieve(0));


    }

    @Test
    public void deleteTest(){

        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        for(int index = 0 ; index <= 5; index++){
            list.insert(index, new Integer(index));
        }

        System.out.println(list.toString());

        list.delete(2);

        System.out.println(list.toString());
        for(int index = 0 ; index <= 4; index++){
            list.delete(0);
            System.out.println(list.toString());
        }


        list.insert(0, new Integer(1));
        System.out.println(list.toString());
        list.insert(0, new Integer(2));
        System.out.println(list.toString());

    }

}