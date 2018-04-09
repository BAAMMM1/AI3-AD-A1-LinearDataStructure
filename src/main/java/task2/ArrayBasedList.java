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

    public ArrayBasedList() {
        this.maxSize = 12;
        this.size = 0;
        this.elements = (T[])new Object[maxSize];
    }

    private void changeSize(int factor){
        this.maxSize = this.size * factor;
        T[] temp = (T[])new Object[maxSize];
        System.arraycopy(this.elements, 0, temp, 0, this.elements.length);
        this.elements = temp; // TODO - Mit rein ins System.arraycopy)

    }

    private void prePosition(int position) throws IllegalArgumentException{

        if( position > size || position < 0 ){
            throw new IllegalArgumentException();
        }

    }

    /*
    private ArrayBasedList(int arraysize){

        if (arraysize>maxSize){
            new ArrayBasedList(arraysize + maxSize);
        };
        ArrayBasedList<T> returni = null;
        for (int i = 0; i< arraysize; i++){
                returni.insert(i, null);
        }


    };
    */




    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinearList<T> insert(int position, T element) throws IllegalArgumentException {

        // 1. precondition check
        this.prePosition(position);
        if (element == null) throw new IllegalArgumentException();

        // 2. Parameter Valid
        // Alle bis position nach rechts verschieben
        for(int index = size-1; index == position; index--){
            System.out.println("index vor Veränderung: " + index);
            System.out.println("this.element[+1] = " + this.elements[index+1]);
            System.out.println("this element = "+ this.elements[index]);
            System.out.println("---------------------------------------");
            this.elements[index+1] = this.elements[index];
            System.out.println("index: " + index);
        }
        // 3. Element einfügen
        this.elements[position] = element;
        this.size++;

        // 4. Größe anpassen
        if(this.size == maxSize){
            this.changeSize(2);
        }


        return this;
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

    private ArrayBasedList<T> minimize(ArrayBasedList<T> toShortenList){
        if (toShortenList.size()< maxSize/4){
            toShortenList.extract(0, maxSize/2);
        };
        return toShortenList;
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
