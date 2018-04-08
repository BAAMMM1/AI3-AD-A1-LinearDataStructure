package abstractDatatypes.LineareListen;

/**
 * @author Chris on 01.04.2018
 */
public class DoppeltVerketteListe<T> {

    /**
     * Kopf der Liste
     */
    Knoten<T> head;
    /**
     * Ende der Liste
     */
    Knoten<T> tail;

    public DoppeltVerketteListe<T> insert(T element){
        return null;
    }

    public DoppeltVerketteListe<T> delete(T element){
        return null;
    }

    public Knoten<T> find(T element){
        return null;
    }

    public T retrieve(Knoten<T> node){
        return null;
    }

    /**
     * Übung 2.5
     * @param bestehenderKnoten
     * @param einfuegeKnoten
     */
    public void fuegeEinNach(Knoten<T> bestehenderKnoten, Knoten<T> einfuegeKnoten){

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
