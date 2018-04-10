package task2;

import task1.ILinearList;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 09.04.2018
 */
public class ArrayBasedList<T> implements ILinearList<T> {

    private T elements[];
    private int size;
    private int maxSize;

    private static final int MAX_SIZE_INIT = 5;

    public ArrayBasedList() {
        this.maxSize = MAX_SIZE_INIT;
        this.size = 0;
        this.elements = (T[])new Object[maxSize];
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void insert(int position, T element) throws IllegalArgumentException {

        // 1. precondition check
        if(!(0 < position || position <= size))  throw new IllegalArgumentException();

        if (element == null) throw new IllegalArgumentException();

        // 2. Parameter Valid
        // Alle bis position nach rechts verschieben
        for(int index = size-1; index >= position; index--){
            this.elements[index+1] = this.elements[index];

        }
        // 3. Element einfügen
        this.elements[position] = element;
        this.size++;

        // 4. Größe anpassen
        if(this.size == maxSize){
            this.changeSize(2);
        }



    }

    @Override
    public void delete(int position) throws IllegalArgumentException {

        // 1. precondition check 0 = 0 ; 5 = 5 da P0....
        if(!(0 < position || position < size)) throw new IllegalArgumentException();

        // 2. Element löschen aus dem Array
        for(int index = position; index < this.size; index++){
            this.elements[index] = this.elements[index+1];

        }
        // 3. Listen size reduzieren
        this.size--;

        // 4. Array Größe anpassen
        if(this.size == (int)(maxSize*0.25) ){
            this.changeSize(0.5);
        }


    }

    @Override
    public T retrieve(int position) throws IllegalArgumentException {

        if(! (0 <= position || position < this.size)) throw new IllegalArgumentException();

        return this.elements[position];

    }

    @Override
    public void clean() {

        this.maxSize = MAX_SIZE_INIT;
        this.elements = (T[])new Object[maxSize];

    }

    @Override
    public void concat(ILinearList<T> toAddList) throws IllegalArgumentException {

        if(toAddList == null) throw new IllegalArgumentException();




    }

    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {
        return null;
    }


    @Override
    public String toString() {
        return "ArrayBasedList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", maxSize=" + maxSize +
                '}';
    }



    private void changeSize(double factor){
        this.maxSize = (int)(this.maxSize * factor);
        T[] temp = (T[])new Object[maxSize];

        System.arraycopy(this.elements, 0, temp, 0, this.size);
        this.elements = temp; // TODO - Mit rein ins System.arraycopy)

    }


}
