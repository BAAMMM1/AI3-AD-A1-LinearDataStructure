package task2;

import task1.ILinearList;

import java.util.AbstractList;

/**
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedList<T> extends AbstractLinearList<T> {

    /**
     * Kopf der Liste.
     */
    Node<T> head;

    /**
     * Ende der Liste.
     */
    Node<T> tail;


    /**
     * Konstruktor: Erstellt eine leere doppelt verlinkte Liste da.
     */
    public DoubleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    /**
     * Fügt Element in einer bestimmten Position der Liste hinzu.<p>
     * <p>
     * Operation insert:    LIST x POS x ELEM -> LIST
     * Pre:                 position element {position-0, ...,position-n} erlaubte Position
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     * Sei ai das Element an Position pi. Dann bewirkt
     * L.insert(position-i,x) = (a0,....,ai-1,x,ai,ai+1,...an)
     *
     * @param position 0 <= position <= size
     * @param element  darf nicht null sein
     * @throws IllegalArgumentException wenn nicht 0 <= position <= size
     */
    @Override
    public void insert(int position, T element) throws IllegalArgumentException {

        //1. preconditions check
        if (!(0 <= position && position <= size)) throw new IllegalArgumentException();
        //if(element == null) throw new IllegalArgumentException();

        //2. Fall Unterscheidung
        Node<T> node = new Node<T>(element);

        if (head == null) {

            this.head = node;
            this.tail = node;

        } else if (position == 0) {

            // Falls am Anfang eingefügt werden soll, muss der Head neu gesetzt werden
            node.setNext(this.head);
            this.head.setPrev(node);
            this.head = node;

        } else if (position == this.size) {

            // Falls am Ende eingesetzt wird, muss der Tail neu gesetzt werden.
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;

        } else {

            Node<T> nodeOnPosition = null;

            // Falls die Position in der ersten helfte liegt iteriere vom Head aus
            // Falls die Position in der zweiten helfte liegt, iteriere vom Tail aus
            if (position <= (this.size / 2)) {
                nodeOnPosition = this.iteratedFromHead(position);
            } else {
                nodeOnPosition = this.iteratedFromTail(position);
            }

            node.setPrev(nodeOnPosition.getPrev());
            node.setNext(nodeOnPosition);
            nodeOnPosition.getPrev().setNext(node);
            nodeOnPosition.setPrev(node);

        }

        // 3. Elementenanzahl erhöhen
        this.size++;


    }


    /**
     * Löscht ein Element aus einer Liste an einer bestimmten Position.<p>
     * <p>
     * Operation delete:    LIST x POS -> LIST
     * pre:                 position element {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     * Sei ai das Element an Position pi. Dann bewirkt
     * L.delete(position-i) = (a0,....,ai-1,ai+1,...an)
     *
     * @param position 0 <= position < size
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public void delete(int position) throws IllegalArgumentException {

        if (!(0 <= position && position < size)) throw new IllegalArgumentException();

        if(head.equals(tail)) {

            this.head = null;
            this.tail = null;

        } else if(position == 0){

            this.head.getNext().setPrev(null);
            //this.head.setNext(null);
            this.head = this.head.getNext();

        } else if( position == this.size-1){

            this.tail.getPrev().setNext(null);
            //this.tail.setPrev(null);
            this.tail = this.tail.getPrev();

        } else {

            Node<T> nodeOnPosition = null;

            // Falls die Position in der ersten helfte liegt iteriere vom Head aus
            // Falls die Position in der zweiten helfte liegt, iteriere vom Tail aus
            if (position <= (this.size / 2)) {
                nodeOnPosition = this.iteratedFromHead(position);
            } else {
                nodeOnPosition = this.iteratedFromTail(position);
            }

            nodeOnPosition.getNext().setPrev(nodeOnPosition.getPrev());
            nodeOnPosition.getPrev().setNext(nodeOnPosition.getNext());
            //nodeOnPosition.setNext(null);
            //nodeOnPosition.setPrev(null);

        }

        this.size--;

    }

    /**
     * Gibt ein Element einer Liste von einer Position zurück.<p>
     * <p>
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position element {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     * L.retrieve(position-i) = ai
     *
     * @param position 0 <= position < size
     * @return Das Element an der Position aus der Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public T retrieve(int position) throws IllegalArgumentException {

        if (!(0 <= position && position < size)) throw new IllegalArgumentException();

        if(position <= (this.size/2)){
            return this.iteratedFromHead(position).getElement();
        } else {
            return this.iteratedFromTail(position).getElement();
        }

    }

    /**
     * Leere die Liste.<p>
     * <p>
     * Operation clean: LIST -> LIST
     * pre:
     * post:            Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     * L.clean() = L{};
     */
    @Override
    public void clean() {

        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    private Node<T> iteratedFromHead(int position) {

        if(head == null) throw new IllegalArgumentException();

        Node<T> result = this.head;

        for (int i = 1; i <= position; i++) {
            result = result.next;
        }

        return result;
    }

    private Node<T> iteratedFromTail(int position) {

        if(tail == null) throw new IllegalArgumentException();

        Node<T> result = this.tail;

        for (int i = this.size-1; i > position; i--) {
            result = result.prev;
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleLinkedList<?> that = (DoubleLinkedList<?>) o;

        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        return tail != null ? tail.equals(that.tail) : that.tail == null;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (tail != null ? tail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        Node<T> node = head;
        String nodes = "";

        while (node != null) {
            nodes = nodes + node.toString() + ", ";
            node = node.next;
        }

        //nodes = nodes.substring(0, nodes.length()-2);

        return "DoubleLinkedList{" + nodes + '}' + "size: " + this.size;
    }

    /**
     * Stellt einen Knoten der doppelt verlinkten Liste da.
     *
     * @param <T>
     */
    private static class Node<T> {

        /**
         * Daten des Knoten
         */
        T element;

        /**
         * Vörgänger Knoten, falls Knoten head darstellt null.
         */
        Node<T> prev;

        /**
         * Nachfolger Knoten, falls Knoten tail darstellt null.
         */
        Node<T> next;

        public Node(T element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }

        public Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return element != null ? element.equals(node.element) : node.element == null;
        }

        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (prev != null ? prev.hashCode() : 0);
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {

            String prevS = null;
            if (this.prev != null) {

                if (prev.element != null) {
                    prevS = prev.element.toString();
                }

            } else {
                prevS = null;
            }

            String nextS = null;
            if (this.next != null) {

                if (next.element != null) {
                    nextS = next.element.toString();
                }

            } else {
                nextS = null;
            }

            return "Node{" +
                    "element=" + element +
                    ", prev=" + prevS +
                    ", next=" + nextS +
                    '}';
        }
    }
}
