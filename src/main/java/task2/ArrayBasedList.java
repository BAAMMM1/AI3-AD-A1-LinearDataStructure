package task2;

import task1.ILinearList;

/**
 * @author Chris on 09.04.2018
 */
public class ArrayBasedList<T> implements ILinearList<T> {

    private T elements[];
    private int size;
    private int maxSize;

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinearList<T> insert(int position, T element) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ILinearList<T> delete(int position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public T retrieve(int position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ILinearList<T> clean() {
        return null;
    }

    @Override
    public ILinearList<T> concat(ILinearList<T> toAddList) throws IllegalArgumentException {
        return null;
    }

    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {
        return null;
    }
}
