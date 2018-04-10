package task2;

import task1.ILinearList;

import java.util.LinkedList;

/**
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedList<T> implements ILinearList<T> {

    /**
     * Kopf der Liste
     */
    Knoten<T> head;

    /**
     * Ende der Liste
     */
    Knoten<T> tail;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void insert(int position, T element) throws IllegalArgumentException {

    }

    @Override
    public void delete(int position) throws IllegalArgumentException {

    }

    @Override
    public T retrieve(int position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void clean() {

    }

    @Override
    public void concat(ILinearList<T> toAddList) throws IllegalArgumentException {

    }

    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {
        return null;
    }


    static class Knoten<T> {

        T element;
        Knoten<T> prev;
        Knoten<T> next;

        public Knoten(T element, Knoten<T> prev, Knoten<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
