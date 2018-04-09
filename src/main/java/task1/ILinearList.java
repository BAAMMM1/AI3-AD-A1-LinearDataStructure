package task1;

import java.util.List;

/**
 * @author Shadai on 09.04.2018
 */
public interface ILinearList<T> {
    /**
     * Zählt die Elemente einer Liste<p>
     *
     * Operation size:  LIST -> INT
     * pre:             Da keine Parameter übergeben werde, werden keine PreConditions benötigt
     * post:            Liste unverändert
     *
     * @return bei leerer Liste wird 0 zurückgegeben, sonst die Anzahl der Elemente in der Liste
     */
    int size();

    /**
     * Fügt Element in einer bestimmten Position der Liste hinzu.<p>
     *
     * Operation insert:    LIST x POS x ELEM -> LIST
     * Pre:                 position ∈ {position-0, ...,position-n} erlaubte position
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.insert(position-i,x) = (a0,....,ai-1,x,ai,ai+1,...an+1)
     *
     * @param position Wert muss >= 0 sein und <= Listsize-1                                         gültigkeitsbereiche
     * @param element darf nicht null sein
     * @return um element an postion erweiterte Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    ILinearList<T> insert(int position, T element) throws IllegalArgumentException;

    /**
     * Löscht ein Element aus einer Liste an einer bestimmten Position.<p>
     *
     * Operation delete:    LIST x POS -> LIST
     * pre:                 position ∈ {position0, ..., positionn} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.delete(positioni) = (a0,....,ai-1,ai+1 = ai-1,...an = an-1)
     *
     * @param position Wert muss >= 0 sein und <= Listsize-1
     * @return um 1 element an postion reduzierte Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    ILinearList<T> delete(int position) throws IllegalArgumentException;

    /**
     * Gibt ein Element einer Liste von einer Position zurück.<p>
     *
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position ∈ {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                      L.retrieve(positioni) = ai
     *
     * @param position position Wert muss >= 0 sein und <= Listsize-1
     * @return ein Element aus position der Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    T retrieve(int position) throws IllegalArgumentException;

    /**
     * Gibt eine leere Liste zurück.<p>
     *
     * Operation clean: LIST -> LIST
     * pre:
     * post:            Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                  L.clean() = L{};
     *
     * @return  gibt eine leere Liste zurück
     */
    ILinearList<T> clean();

    /**
     * Fügt zwei Listen zusammen.<p>
     *
     * Operation concat:    LIST x LIST -> LIST
     * pre:
     * post:                Sei L1 = (a0,...,an) eine lineare Liste.
     *                      Sei L2 = (b0,...,bn) eine lineare Liste.
     *                      Dann bewirkt L1.concat(L2) = (a0,...,an,b0,...,bn)
     *                      falls L2{} dann bewirkt L1.concat(L2) = L1
     *
     * @param toAddList Liste mit n Elementen oder leere Liste
     * @return um otherList erweiterte Liste
     * @throws IllegalArgumentException falls otherList null ist
     */
    ILinearList<T> concat(ILinearList<T> toAddList) throws IllegalArgumentException;

    /**
     * Extrahiert eine Subliste innerhalb 2er Positionen einer Liste
     *
     * Operation extract:   LIST x INT x INT -> LIST
     * Pre:                 Sei fromPosition ∈ {Position0, ...,Positionn} erlaubte position
     *                      Sei toPosition ∈ {Position0, ...,Positionn} erlaubte position,
     *                      fromPosition < toPosition
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi und ay das Element an der Postition py. Dann bewirkt
     *                      L.extract(positioni,positiony) = (ai,....,ay)
     *
     * @param fromPosition Wert muss >= 0 sein und <= Listsize-1
     * @param toPosition Wert muss >= fromPosition sein und <= Listsize-1
     * @return eine Subliste aus 2er Positionen einer Liste
     */
    ILinearList<T> extract(int fromPosition, int toPosition);


}