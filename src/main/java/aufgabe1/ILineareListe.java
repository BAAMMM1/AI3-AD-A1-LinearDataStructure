package aufgabe1;

import java.util.List;

/**
 * @author Shadai on 09.04.2018
 */
public interface ILineareListe<T> {
    /**
     * Operation size:  LIST -> INT
     * pre:     Da keine Parameter übergeben werde, werden keine PreConditions benötigt
     * post:    Liste unverändert
     *
     * Zählt die Elemente der Liste
     * @return bei leerer Liste wird 0 zurückgegeben, sonst die Anzahl der Elemente in der Liste
     */
    int size();

    /**
     * Operation size:  LIST x POS x ELEM -> LIST
     * Pre:     position ∈ {position-0, ...,position-n} erlaubte position
     * Post:    Sei L = (a0,...,an) eine lineare Liste.
     *          Sei ai das Element an Position pi. Dann bewirkt
     *          L.insert(position-i,x) = (a0,....,ai-1,x,ai,ai+1,...an+1)
     *
     * Fügt Element einer Liste hinzu
     * Fügt Element in einer bestimmten Position der Liste hinzu
     * @param position Wert muss >= 0 sein und <= Listsize-1                                         gültigkeitsbereiche
     * @param element darf nicht null sein
     * @return um element an postion erweiterte Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    ILineareListe<T> insert(int position, T element) throws IllegalArgumentException;

    /**
     * Operation delete:    LIST x POS -> LIST
     * pre:                 position ∈ {position0, ..., positionn} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.delete(positioni) = (a0,....,ai-1,ai+1 = ai-1,...an = an-1)
     * @param position Wert muss >= 0 sein und <= Listsize-1
     * @return um 1 element an postion reduzierte Liste
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    ILineareListe<T> delete(int position) throws IllegalArgumentException;

    /**
     * Operation retrieve:  LIST x POS -> ELEM
     * pre:                 position ∈ {position-0, ..., position-n} erlaubte Position
     * post:                Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                      L.retrieve(positioni) = ai.
     * @param position position Wert muss >= 0 sein und <= Listsize-1
     * @return ein Element aus position der List
     * @throws IllegalArgumentException Position kleiner 0 oder >= Listsize
     */
    T retrieve(int position) throws IllegalArgumentException;

    /**
     * Operation clean:  LIST -> LIST
     * pre:
     * post:                Sei L = (a0,...,an) eine lineare Liste. Dann bewirkt
     *                      L.clean() = L{};
     * @return
     */
    ILineareListe<T> clean();

    /**
     * Fügt zwei Listen zusammenfügen.<p>
     *
     * Operation concat:    LIST x LIST -> LIST
     * pre:
     * post:                Sei L1 = (a0,...,an) eine lineare Liste.
     *                      Sei L2 = (b0,...,bn) eine lineare Liste.
     *                      Dann bewirkt L1.concat(L2) = (a0,...,an,b0,...,bn)
     *                      falls L2{} dann bewirkt L1.concat(L2) = L1
     *
     *
     * @param toAddList Liste mit n Elementen oder leere Liste
     * @return um otherList erweiterte Liste
     * @throws IllegalArgumentException falls otherList null ist
     */
    ILineareListe<T> concat(ILineareListe<T> toAddList) throws IllegalArgumentException;

    /**
     * Operation extract:   LIST x INT x INT -> LIST
     * Pre:                  Sei fromPosition ∈ {Position0, ...,Positionn} erlaubte position
     *                      Sei toPosition ∈ {Position0, ...,Positionn} erlaubte position,
     *                      fromPosition < toPosition
     * Post:                Sei L = (a0,...,an) eine lineare Liste.
     *                      Sei ai das Element an Position pi und ay das Element an der Postition py. Dann bewirkt
     *                      L.extract(positioni,positiony) = (ai,....,ay)
     *
     * @param fromPosition
     * @param toPosition
     * @return
     */
    ILineareListe<T> extract(int fromPosition, int toPosition);


}
// Abfrage der Anzahl der Elemente
//         Einfügen an beliebiger (gültiger) Position
//         Löschen von beliebiger (gültiger) Position
//         Elementzugriff an beliebiger (gültiger) Position
//         Leeren der Liste
//         Zusammenfügen von zwei Listen
//         Subliste extrahieren