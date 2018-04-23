package task2;

import task1.ILinearList;

/**
 * @author Chris on 12.04.2018
 */
public abstract class AbstractLinearList<T> implements ILinearList<T> {


    /**
     * Anzahl an Elementen in der Liste.
     */
    int size;

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

        // Size der toAddList nicht dynamisch nachfragen, da l1.concat(L1) sonst unendlich laufen würde.
        int toAddSize = toAddList.size();

        for (int i = 0; i < toAddSize; i++) {
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

        ILinearList<T> result = null;

        try {
            result = this.getClass().newInstance();

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        for (int i = fromPosition; i <= toPosition; i++) {
            result.insert(result.size(), this.retrieve(i));
        }

        return result;

    }




}
