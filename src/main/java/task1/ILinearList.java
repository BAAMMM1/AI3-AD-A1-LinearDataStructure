package task1;

import java.util.List;

/**
 * Dieses Interface stellt die Schnittstelle des abstarcten Datentyps lineare Liste da.
 *
 * @author Shadai on 09.04.2018
 */
public interface ILinearList<T> {

    /**
     * Zählt die Elemente einer Liste<p>
     *
     * Operation size:  LIST -> INT
     * pre:
     * post:            Liste ist unverändert
     *
     * @return bei leerer Liste wird 0 zurükgegeben, sonst die Anzahl der Elemente in der Liste
     */
    int size();

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
    void insert(int position, T element) throws IllegalArgumentException;

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
    void delete(int position) throws IllegalArgumentException;

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
    T retrieve(int position) throws IllegalArgumentException;

    /**
     * Leere die Liste.<p>
     *
     * Operation clean: LIST -> LIST
     * pre:
     * post:            Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                  L.clean() = L{};
     */
    void clean();

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
    void concat(ILinearList<T> toAddList) throws IllegalArgumentException;

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
    ILinearList<T> extract(int fromPosition, int toPosition);


}