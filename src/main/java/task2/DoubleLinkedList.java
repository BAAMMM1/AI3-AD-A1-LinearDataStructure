package task2;

/**
 * Stellt den abstrakten Datenytp der doppelt verketteten Liste da
 * Dafür werden Knoten und Vorgänger und Nachfolger Knoten erstellt
 * Die Liste kennt den Anfang und das Ende der verketeten Knoten
 *
 * @author Chris on 09.04.2018
 */
public class DoubleLinkedList<T> extends AbstractLinearList<T> {

    /**
     * Kopf der Liste.
     */
    private Node<T> head;

    /**
     * Ende der Liste.
     */
    private Node<T> tail;


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
     * Pre:                 position {position-0, ...,position-n} erlaubte Position
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
        if (element == null) throw new IllegalArgumentException();



        //2. Fall Unterscheidung
        if (head == null) {
            Node<T> tailHeadNode = new Node<T>(element);
            //2.1 Liste ist komplett Leer
            this.head = tailHeadNode;
            this.tail = tailHeadNode;

        } else if (position == 0) {
            Node<T> newHeadNode = new Node<T>(element);
            //2.2 Element am Anfang einfügen
            // Falls am Anfang eingefügt werden soll, muss der Head neu gesetzt werden
            newHeadNode.setNext(this.head);    // Nachfolger des neuen Knoten = alter Head
            this.head.setPrev(newHeadNode);    // Vorgänger des alten Head = neuer Knoten
            this.head = newHeadNode;           // Neuer Knoten wird Head

        } else if (position == this.size) {
            Node<T> newTailNode = new Node<T>(element);
            //2.3 Element am Ende einfügen
            // Falls am Ende eingesetzt wird, muss der Tail neu gesetzt werden.
            this.tail.setNext(newTailNode);    // Nachfolger des alten Tails = neuer Tail
            newTailNode.setPrev(this.tail);    // Vorgänger des neuen Tails = alter Tail
            this.tail = newTailNode;           // Neuer Knoten wird zu Tail

        } else {
            Node<T> node = new Node<T>(element);
            //2.4 Element wird mitten drin eingefügt
            Node<T> nodeOnPosition;

            if (position <= (this.size / 2)) {
                //2.4.1 Falls die Position in der ersten helfte liegt iteriere vom Head aus
                nodeOnPosition = this.iteratedFromHead(position);
            } else {
                //2.4.2 Falls die Position in der zweiten helfte liegt, iteriere vom Tail aus
                nodeOnPosition = this.iteratedFromTail(position);
            }

            //               oldprev(nodeOnPositionPrev)    new(node)                   oldnext(nodeOnPosition)
            //      prev                                    oldprev(oldnext.getprev)    node
            //      next     node                           oldnext

            //1. Neuen Knoten erstellen -> Vorgänger und Nachfolger setzen (noch nicht integriert in Liste)
            node.setPrev(nodeOnPosition.getPrev());
            node.setNext(nodeOnPosition);

            //2. Alte Knoten -> neuen Vorgänger und Nachfolger setzen (Integriereung des neuen Knoten in Liste)
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
     * pre:                 position {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     * Sei ai das Element an Position pi. Dann bewirkt
     * L.delete(position-i) = (a0,....,ai-1,ai+1,...an)
     *
     * @param position 0 <= position < size
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public void delete(int position) throws IllegalArgumentException {

        // 1. Preconditoncheck
        if (!(0 <= position && position < size)) throw new IllegalArgumentException();

        // 2. Fallunterscheidung
        if(head.equals(tail)) {
            // 2.1 Nur 1 Element in Liste
            this.head = null;
            this.tail = null;

        } else if(position == 0){
            // 2.2 Head soll gelöscht werden
            // Nachfolgeknoten -> Vorgänger auf Null (Head) setzen
            this.head.getNext().setPrev(null);
            // Nochfolgeknoten zum Head bestimmen
            this.head = this.head.getNext();

        } else if( position == this.size-1){
            // 2.3 Tail soll gelöscht werden
            // analog zu Head nur umgekehrt
            this.tail.getPrev().setNext(null);
            this.tail = this.tail.getPrev();

        } else {
            // 2.4 Node mitten drin soll gelöscht werden
            Node<T> nodeOnPosition;

            // Iteriere zur Position des Nodes
            // Falls die Position in der ersten helfte liegt iteriere vom Head aus
            // Falls die Position in der zweiten helfte liegt, iteriere vom Tail aus
            if (position <= (this.size / 2)) {
                nodeOnPosition = this.iteratedFromHead(position);
            } else {
                nodeOnPosition = this.iteratedFromTail(position);
            }

            // Ausgliederung des Knotens an Position
            // Setze vom Nachfogleknotens den Vorgänger auf den Vorgängerknoten der Position
            nodeOnPosition.getNext().setPrev(nodeOnPosition.getPrev());
            // Setze vom Vorgängerknoten den Nachfolger auf den Nachfolgeknoten der Position
            nodeOnPosition.getPrev().setNext(nodeOnPosition.getNext());

        }

        this.size--;

    }

    /**
     * Gibt ein Element einer Liste von einer Position zurück.<p>
     * <p>
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position {position-0, ..., position-n} erlaubte Position
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

    /**
     * Iteriert über die verkettete Liste vom Kopf und ermittelt den Knoten an der übergebenen Position.
     *
     * @param position 0 <= position <= size
     * @return ermittelten Knoten an der Position
     */
    private Node<T> iteratedFromHead(int position) {

        if (!(0 <= position && position <= size)) throw new IllegalArgumentException();
        if(head == null) throw new IllegalArgumentException();

        Node<T> result = this.head;
        // i = 1 da nicht head
        for (int i = 1; i <= position; i++) {
            result = result.getNext();
        }

        return result;
    }

    /**
     * Iteriert über die verkettete Liste vom Schwanz und ermittelt den Knoten an der übergebenen Position.
     *
     * @param position 0 <= position <= size
     * @return ermittelten Knoten an der Position
     */
    private Node<T> iteratedFromTail(int position) {

        if (!(0 <= position && position <= size)) throw new IllegalArgumentException();
        if(tail == null) throw new IllegalArgumentException();

        Node<T> result = this.tail;
        // size -1 für Position; position-1 für nicht tail, daher size -2
        for (int i = this.size-2; i >= position; i--) {
            result = result.getPrev();
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
        StringBuilder nodes = new StringBuilder();

        while (node != null) {
            nodes.append(node.toString()).append(", ");
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

        /**
         * Konstruiert einen Knoten mit einem Element und prev und next Schnittstelle
         *
         * @param element darf nicht null sein
         */
        private Node(T element) {
            if (element == null) throw new IllegalArgumentException();
            this.element = element;
            this.prev = null;
            this.next = null;
        }

        private T getElement() {
            return element;
        }

        private Node<T> getPrev() {
            return prev;
        }

        private void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        private Node<T> getNext() {
            return next;
        }

        private void setNext(Node<T> next) {
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

            String prevS = "";
            if (this.prev != null) {

                if (prev.element != null) {
                    prevS = prev.element.toString();
                }

            }

            String nextS = "";
            if (this.next != null) {

                if (next.element != null) {
                    nextS = next.element.toString();
                }

            }

            return "Node{" +
                    "element=" + element +
                    ", prev=" + prevS +
                    ", next=" + nextS +
                    '}';
        }
    }
}
