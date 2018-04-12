package task2;

import task1.ILinearList;

import java.util.Arrays;

/**
 * Diese Klasse stellt eine Array basierte lineare Liste da.
 *
 * @author Chris on 09.04.2018
 * @version 1.0
 */
public class ArrayBasedList<T> implements ILinearList<T> {

    /**
     * Bestimmt um welchen Faktor das Array vergrößert wird.
     */
    private static final int INCREASE_FACTOR = 2;

    /**
     * Bestimmt um welchen Faktor das Array verkleinert wird.
     */
    private static final double DECRASE_FACTOR = 0.5;

    /**
     * Bestimmt ab welcher prozentualen Größe des Inhalt zur Kapazität des Arrays das Array verkleinert wird.
     */
    private static final double DECREASE_PERCENT_SIZE = 0.25;

    /**
     * Initial Kapazität des Arrays.
     */
    private static final int MAX_SIZE_INIT = 12;

    /**
     * Array das die Elemente speichert.
     */
    private T elements[];

    /**
     * Anzahl an Elementen des Arrays.
     */
    private int size;

    /**
     * Kapazität des Arrays.
     */
    private int maxSize;


    /**
     * Konstruktor: Liefert eine leere Array basierte Liste zurück.
     */
    public ArrayBasedList() {
        this.maxSize = MAX_SIZE_INIT;
        this.size = 0;
        this.elements = (T[]) new Object[maxSize];
    }

    // TODO - List Konstruktor

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
        return size;
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

        // 1. precondition check
        if (!(0 <= position && position <= size)) throw new IllegalArgumentException();

        if (element == null) throw new IllegalArgumentException();

        // 2. Alle Elemente von hinten eine position nach rechts verschieben
        for (int index = size - 1; index >= position; index--) {
            this.elements[index + 1] = this.elements[index];
        }

        // 3. Element einfügen
        this.elements[position] = element;
        this.size++;

        // 4. Größe anpassen
        if (this.size == maxSize) {
            this.adjustSize(INCREASE_FACTOR);
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

        // 1. precondition check 0 = 0 ; 5 = 5 da P0....
        if (!(0 <= position && position < size)) throw new IllegalArgumentException();

        // 2. Element löschen aus dem Array
        for (int i = position; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        // 3. Listen size reduzieren
        this.size--;

        // 4. Array Größe anpassen
        if (this.size == (int) (maxSize * DECREASE_PERCENT_SIZE)) {
            this.adjustSize(DECRASE_FACTOR);
        }
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

        if (!(0 <= position && position < this.size)) throw new IllegalArgumentException();

        return this.elements[position];

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

        this.maxSize = MAX_SIZE_INIT;
        this.elements = (T[]) new Object[maxSize];
        this.size = 0;

    }

    /**
     * Fügt zwei Listen zusammen.<p>
     * <p>
     * Operation concat:    LIST x LIST -> LIST
     * pre:
     * post:                Sei L1 = (a0,...,an) eine lineare Liste.
     * Sei L2 = (b0,...,bn) eine lineare Liste.
     * Dann bewirkt L1.concat(L2) = (a0,...,an,b0,...,bn)
     * falls L2={} dann bewirkt L1.concat(L2) = L1
     * falls L1={} dann bewirkt L1.concat(L2) = L2
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
     * <p>
     * Operation extract:   LIST x INT x INT -> LIST
     * Pre:                 Sei fromPosition element {Position-0, ...,Position-n} erlaubte position
     * Sei toPosition element {Position-0, ...,Position-n} erlaubte position,
     * fromPosition <= toPosition
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     * Sei ai das Element an Position pi und ay das Element an der Postition py. Dann bewirkt
     * L.extract(position-i,position-y) = (ai,....,ay)
     *
     * @param fromPosition 0 <= fromPosition < size
     * @param toPosition   fromPosition <= toPosition < size <p>
     *                     0 <= fromPosition <= toPosition < size
     * @return eine Subliste aus 2er Positionen einer Liste
     */
    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {

        if (!(0 <= fromPosition && fromPosition < size)) throw new IllegalArgumentException();
        if (!(fromPosition <= toPosition && toPosition < this.size)) throw new IllegalArgumentException();

        ArrayBasedList<T> result = new ArrayBasedList<T>();

        for (int i = fromPosition; i <= toPosition; i++) {
            result.insert(result.size(), this.retrieve(i));
        }

        return result;
    }

    /**
     * Passt die Länge des Arrays um den übergebenen Faktor an.
     *
     * @param factor 0 <= factor
     * @exception IllegalArgumentException falls nicht 0 <= factor
     */
    private void adjustSize(double factor) {

        if(!(0 <= factor)) throw new IllegalArgumentException();

        this.maxSize = (int) (this.maxSize * factor);
        T[] temp = (T[]) new Object[maxSize];

        System.arraycopy(this.elements, 0, temp, 0, this.size);
        this.elements = temp;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayBasedList<?> that = (ArrayBasedList<?>) o;

        if (size != that.size) return false;
        if (maxSize != that.maxSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(elements);
        result = 31 * result + size;
        result = 31 * result + maxSize;
        return result;
    }

    @Override
    public String toString() {
        return "ArrayBasedList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", maxSize=" + maxSize +
                '}';
    }


}
