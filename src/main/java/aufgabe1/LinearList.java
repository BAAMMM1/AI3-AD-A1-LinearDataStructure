package aufgabe1;

/**
 * @author Chris on 08.04.2018
 */
public interface LinearList<T> {

    int numbersOfElements();


    /**
     *  Operation insert:   LIST x POS x ELEM -> LIST; L.insert(p,x)
     *  pre:                position ∈ {position-0, ..., position-n} erlaubte Position
     *  post:               Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.insert(position-i,x) = (a0,....,ai-1,x,ai,ai+1,...an+1)
     *
     * Einfügen an beliebiger (gültiger) Position
     *
     * @param position
     * @param element darf nicht null sein
     * @return um element an position erweiterte Liste
     * @throws IllegalArgumentException falls position eine unerlaubte Position ist oder falls element null
     */
    LinearList<T> insert(int position, T element) throws IllegalArgumentException;

    LinearList<T> delete(int position) throws IllegalArgumentException;
    T retrieve(int position) throws IllegalArgumentException;
    LinearList<T> deleteAll();
    LinearList<T> concat(LinearList<T> otherList) throws IllegalArgumentException;

    // TODO - Welche Parameter benötigt diese Methode?
    LinearList<T> getSublist(int rangeStart, int rangeEnd) throws IllegalArgumentException;

}
