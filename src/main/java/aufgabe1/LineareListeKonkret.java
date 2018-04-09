package aufgabe1;

/**
 * @author Shadai on 09.04.2018
 */
public class LineareListeKonkret<T> implements ILineareListe<T> {


    @Override
    public int numberOfElements() {
        return 488;
    }

    @Override
    public ILineareListe<T> insert(int position, T element) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ILineareListe<T> delete(int position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public T retrieve(int position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ILineareListe<T> clean() {
        return null;
    }

    @Override
    public ILineareListe<T> concat(ILineareListe<T> toAddList) {
        return null;
    }

    @Override
    public ILineareListe<T> extract(int fromPosition, int toPosition) {
        return null;
    }


}
