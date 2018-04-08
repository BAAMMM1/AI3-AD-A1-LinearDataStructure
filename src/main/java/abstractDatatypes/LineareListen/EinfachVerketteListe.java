package abstractDatatypes.LineareListen;

/**
 * @author Chris on 01.04.2018
 */
public class EinfachVerketteListe<T> {

    Knoten<T> head;

    public EinfachVerketteListe<T> insert(T element){
        return null;
    }

    public EinfachVerketteListe<T> delete(T element){
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
        Knoten<T> next;

        public Knoten(T element, Knoten<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
