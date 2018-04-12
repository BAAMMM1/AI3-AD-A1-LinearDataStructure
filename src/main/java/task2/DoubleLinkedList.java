package task2;

import task1.ILinearList;

/**
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedList<T> implements ILinearList<T> {

    /**
     * Kopf der Liste.
     */
    Node<T> head;

    /**
     * Ende der Liste.
     */
    Node<T> tail;

    /**
     * Anzahl an Elementen in der Liste.
     */
    int size;

    /**
     * Konstruktor: Erstellt eine leere doppelt verlinkte Liste da.
     */
    public DoubleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Zählt die Elemente einer Liste<p>
     *
     * Operation size:  LIST -> INT
     * pre:
     * post:            Liste ist unverändert
     *
     * @return bei leerer Liste wird 0 zurükgegeben, sonst die Anzahl der Elemente in der Liste
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Fügt Element in einer bestimmten Position der Liste hinzu.<p>
     *
     * Operation insert:    LIST x POS x ELEM -> LIST
     * Pre:                 position element {position-0, ...,position-n} erlaubte Position
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.insert(position-i,x) = (a0,....,ai-1,x,ai,ai+1,...an)
     *
     * @param position 0 <= position <= size
     * @param element darf nicht null sein
     * @throws IllegalArgumentException wenn nicht 0 <= position <= size
     */
    @Override
    public void insert(int position, T element) throws IllegalArgumentException {

        //1. preconditions check
        if(!(0 <= position && position <= size)) throw new IllegalArgumentException();
        if(element == null) throw new IllegalArgumentException();

        //2. Check ob die Liste leer ist
        if(head == null){

            Node<T> node = new Node<T>(element);

            this.head = node;
            this.tail = node;
            this.size++;

        } else if(head == tail){

            Node<T> node = new Node<T>(element);

            this.tail = node;
            this.head.setNext(node);
            this.tail.setPrev(this.head);
            this.size++;

        } else if(position == this.size) {

            //System.out.println(3);

            Node<T> node = new Node<T>(element);

            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;

            this.size++;


        } else if(position == 0){ // TODO tut der hier not
            //System.out.println(4);

            Node<T> node = new Node<T>(element);

            node.setNext(this.head);
            this.head.setPrev(node);
            this.head = node;
            this.size++;

        } else { // TODO - von link und von rechts
            //System.out.println(5);

            Node<T> node = new Node<T>(element);

            Node<T> positionNode = this.head;
            int index = 0;
            do {

                positionNode = positionNode.next;
                index++;

            } while(index < position);

            node.setPrev(positionNode.prev);
            node.setNext(positionNode);

            positionNode.getPrev().setNext(node);
            positionNode.setPrev(node);

            this.size++;

        }



    }

    /**
     * Löscht ein Element aus einer Liste an einer bestimmten Position.<p>
     *
     * Operation delete:    LIST x POS -> LIST
     * pre:                 position element {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.delete(position-i) = (a0,....,ai-1,ai+1,...an)
     *
     * @param position 0 <= position < size
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public void delete(int position) throws IllegalArgumentException {

    }

    /**
     * Gibt ein Element einer Liste von einer Position zurück.<p>
     *
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position element {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                      L.retrieve(position-i) = ai
     *
     * @param position 0 <= position < size
     * @return Das Element an der Position aus der Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public T retrieve(int position) throws IllegalArgumentException {

        return null;
    }

    /**
     * Leere die Liste.<p>
     *
     * Operation clean: LIST -> LIST
     * pre:
     * post:            Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                  L.clean() = L{};
     */
    @Override
    public void clean() {

        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    /**
     * Fügt zwei Listen zusammen.<p>
     *
     * Operation concat:    LIST x LIST -> LIST
     * pre:
     * post:                Sei L1 = (a0,...,an) eine lineare Liste.
     *                      Sei L2 = (b0,...,bn) eine lineare Liste.
     *                      Dann bewirkt L1.concat(L2) = (a0,...,an,b0,...,bn)
     *                      falls L2={} dann bewirkt L1.concat(L2) = L1
     *                      falls L1={} dann bewirkt L1.concat(L2) = L2
     *
     * @param toAddList Liste mit n Elementen oder leere Liste
     * @throws IllegalArgumentException falls otherList null
     */
    @Override
    public void concat(ILinearList<T> toAddList) throws IllegalArgumentException {

        if (toAddList == null) throw new IllegalArgumentException();

        for (int i = 0; i < toAddList.size(); i++) {
            this.insert(this.size, toAddList.retrieve(i));
        }

    }

    /**
     * Extrahiert eine Subliste innerhalb 2er Positionen einer Liste
     *
     * Operation extract:   LIST x INT x INT -> LIST
     * Pre:                 Sei fromPosition element {Position-0, ...,Position-n} erlaubte position
     *                      Sei toPosition element {Position-0, ...,Position-n} erlaubte position,
     *                      fromPosition <= toPosition
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi und ay das Element an der Postition py. Dann bewirkt
     *                      L.extract(position-i,position-y) = (ai,....,ay)
     *
     * @param fromPosition 0 <= fromPosition < size
     * @param toPosition fromPosition <= toPosition < size <p>
     * 0 <= fromPosition <= toPosition < size
     * @return eine Subliste aus 2er Positionen einer Liste
     */
    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {

        if (!(0 <= fromPosition && fromPosition < size)) throw new IllegalArgumentException();
        if (!(fromPosition <= toPosition && toPosition < this.size)) throw new IllegalArgumentException();

        DoubleLinkedList<T> result = new DoubleLinkedList<T>();

        for (int i = fromPosition; i <= toPosition; i++) {
            result.insert(result.size(), this.retrieve(i));
        }

        return result;

    }

    @Override
    public String toString() {

        Node<T> node = head;
        String nodes = "";

        while(node != null){
            nodes = nodes + node.toString() + ", ";
            node = node.next;
        }

        return "DoubleLinkedList{" + nodes + '}';
    }

    /**
     * Stellt einen Knoten der doppelt verlinkten Liste da.
     *
     * @param <T>
     */
    static class Node<T> {

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

            if (element != null ? !element.equals(node.element) : node.element != null) return false;
            if (prev != null ? !prev.equals(node.prev) : node.prev != null) return false;
            return next != null ? next.equals(node.next) : node.next == null;
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

            String prevS;
            if(this.prev != null) {
                prevS = prev.element.toString();
            } else {
                prevS= null;
            }

            String nextS;
            if(this.next != null) {
                nextS = next.element.toString();
            } else {
                nextS= null;
            }

            return "Node{" +
                    "element=" + element +
                    ", prev=" + prevS +
                    ", next=" + nextS +
                    '}';
        }
    }
}
