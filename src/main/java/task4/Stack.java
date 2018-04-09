package task4;

import java.util.ArrayList;
import java.util.List;

/**
 * Stack-Implementierung 
 *
 */
public class Stack<T> {

    /**
     * Liste der Elemente im Stack.
     */
    private List<T> elemente = new ArrayList<T>();

    /**
     * Zusätzliches Element auf den Stack legen.
     */
    public void push(T element) {
        elemente.add(element);
    }

    /**
     * Oberstes Element vom Stack nehmen.
     */
    public T pop() {
        if (elemente.size() == 0) {
            throw new IllegalArgumentException("Stack ist leer.");
        }
        T element = elemente.get(elemente.size() - 1);
        elemente.remove(elemente.size() - 1);
        return element;
    }

    /**
     * Liefert das oberste Element des Stacks ohne es vom Stack zu entfernen
     */
    public T top() {
        if (elemente.size() == 0) {
            throw new IllegalArgumentException("Stack ist leer.");
        }
        return elemente.get(elemente.size() - 1);
    }

    /**
     * Liefert wahr, wenn der Stack leer ist, sonst false.
     */
    public boolean isEmpty() {
        return elemente.size() == 0;
    }

    /**
     * Entfernt alle Elemente vom Stack.
     */
    public void clear() {
        elemente.clear();
    }
}
