package abstractDatatypes.LineareListen;

/**
 * @author Chris on 31.03.2018
 */
public class SequenzielleListe<T extends Comparable<T>> {

    /**
     * Die Menge der erlaubten Positionen
     */
    int numbersOfElements;

    /**
     * Die Menge der erlaubten Elemente vom Grundtyp
     */
    int maxElements;

    T[] elements;

    public SequenzielleListe() {
    }

    /**
     *  Operation insert:   LIST x POS x ELEM -> LIST; L.insert(p,x)
     *  pre:                p ∈ {p0, ..., pn} erlaubte Position
     *  post:               Sei L = (a0,...,an) eine SequenzielleListe.
     *                      Sei ai das Element an Position pi. Dann bewirkt
     *                      L.inser(pi,x) = (a0,....,ai-1,x,ai,ai+1,...an+1)
     *
     * Das eimgefügte Element wandert an die angegebene Postion, alle Elemente ab dieser Position wandern eine Position
     * weiter
     * @param position
     * @param element
     * @return
     */
    public SequenzielleListe<T> insert(int position, T element){
        return null;
    }

    /**
     * Operation delete:    LIST x POS -> LIST
     * @param position
     * @return
     */
    public SequenzielleListe<T> delete(int position){
        return null;
    }

    /**
     * Operation find:      LIST x ELEM -> POS
     * @param element
     * @return
     */
    public int find(T element){

        for(int i = 0; i < this.numbersOfElements; i++){

            if( this.elements[i].equals(element)){
                return i + 1;
            }
        }
        return 0;
    }


    /**
     *
     * @param position
     * @return
     */
    public T retrieve(int position){
        return null;
    }

    /**
     * Operation concat:    LIST x LIST -> LIST
     * @param otherSequenzielleListe
     * @return
     */
    public SequenzielleListe<T> conact(SequenzielleListe<T> otherSequenzielleListe){
        return null;
    }


}
