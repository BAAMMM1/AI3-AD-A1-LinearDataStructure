package task2;

import java.util.Arrays;


/**
 * Diese Klasse stellt eine Array basierte lineare Liste da.
 *
 * @author Chris on 09.04.2018
 * @version 1.0
 */
public class ArrayBasedList<T> extends AbstractLinearList<T> {

    /**
     * Bestimmt um welchen Faktor das Array vergrößert wird.
     */
    private static final int INCREASE_FACTOR = 2;

    /**
     * Bestimmt um welchen Faktor das Array verkleinert wird.
     */
    private static final double DECRASE_FACTOR = 0.5;

    /**
     * Bestimmt ab welcher prozentualen Faktor des Inhalt zur Kapazität des Arrays das Array verkleinert wird.
     */
    private static final double DECREASE_PERCENT_SIZE = 0.25;

    /**
     * Initial Kapazität des Arrays.
     */
    private static final int MAX_SIZE_INIT = 12;

    /**
     * Array das die Elemente speichert.
     */
    private T[] elements;

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


    /**
     * Fügt Element in einer bestimmten Position der Liste hinzu.<p>
     *
     * Operation insert:    LIST x POS x ELEM -> LIST
     * Pre:                 position {position-0, ...,position-n} erlaubte Position
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

        // 4. Größe anpassen, da nie eine Liste erstellt werden kann, die mit MaxSize Elementen gefüllt ist,
        // kommen wir nie in den Fall, das wir an eine Liste mit MaxSize gefüllten Elementen versuchen etwas anzuhängen!
        // sobald eine Methode nur 1 Element hinzufügt und dadruch an MaxSize kommt, wird diese sofort erhöht.
        if (this.size == maxSize) {
            this.adjustArraySize(INCREASE_FACTOR);
        }
    }




    /**
     * Löscht ein Element aus einer Liste an einer bestimmten Position.<p>
     *
     * Operation delete:    LIST x POS -> LIST
     * pre:                 position {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.delete(position-i) = (a0,....,ai-1,ai+1,...an)
     *
     * @param position 0 <= position < size
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    @Override
    public void delete(int position) throws IllegalArgumentException {

        // 1. precondition check
        if (!(0 <= position && position < size)) throw new IllegalArgumentException();

        // 2. Element löschen aus dem Array
        // von Position alles ein nach links verschieben
        for (int i = position; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        // 3. Listen size reduzieren
        this.size--;

        // 4. Array Größe anpassen
        if (this.size == (int) (maxSize * DECREASE_PERCENT_SIZE)) {
            this.adjustArraySize(DECRASE_FACTOR);
        }
    }

    /**
     * Gibt ein Element einer Liste von einer Position zurück.<p>
     *
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position {position-0, ..., position-n} erlaubte Position
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
     * Passt die Länge des Arrays um den übergebenen Faktor an.
     *
     * Frage hier: Ist es cleverer mehr Speicher vorzuhalten
     * oder öfter zu kopieren (Leistung vs Kapiztät)
     * genauer gesagt, Faktoranpassung und ggf mehr Stellen leer haben oder
     * Array um fixen Wert erhöhen, dafür ggf mehrfache Anppasung nötig (Stichwort SSD)
     *
     * Wir haben uns hier für eine Faktoranpassung entschieden
     *
     * @param factor 0 <= factor
     * @exception IllegalArgumentException falls nicht 0 <= factor
     */
    private void adjustArraySize(double factor) {
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
