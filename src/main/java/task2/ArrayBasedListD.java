package task2;

import task1.ILinearList;
import task4.Stack;

/**
 * @author Shadai on 24.04.2018
 */

// implements   definiert Schnittstellen, sprich links setzt Schnittstellen von rechts wie folgt um
//              oder anders: ArrayBasedListD<T> implementiert die Schnittstellen von ILinearList<T>
// extends      links leitet von der Oberklasse rechts ab
//              sprich wenn extends am Klassenanfang steht, handelt es sich immer um eine Unterklasse!!!

public class ArrayBasedListD<T> implements ILinearList<T> {
    // 1. Statische Unveränderliche = Deklarierung mit Initialisierung
    /*--------------------------------*/
    private static final int MAX_SIZE = 12;
    private static final double INCREASE_FACTOR = 2.0;
    private static final double DECREASE_LIMIT_FACTOR = 4.0;
    private static final double DECREASE_FACTOR = 0.5;

    // 2. Deklarierung von Instanzvariablen (soweit möglich ohne Initialisierung)
    /*--------------------------------*/
    private double maxSize;
    private int size;
    // private T[] contentTinPrimitiveArray; // nicht nach konvention, obwohl richtig wäre                      //wichtig: Feld ist primitiv
    private T[] tArray;                      // nach konvention wird das eher so geschrieben                    //Liste = Objekt mit Methoden   zumindest in Java
                                             // int lulu[]; ist das gleiche wie int[] lulu1; aber das 2. ist leichter lesbar das es sich um ein ArrayFeld handelt mit Integers gefüllt

    // 3. Constructorphase -> Initialisierung von Instanzvariablen, Überladungen können unterschiedliche Startwerte der Instanzvariablen zurodnen.
    /*--------------------------------*/

    public ArrayBasedListD() {
        this.maxSize = MAX_SIZE;
        size = 0;
        tArray = (T[]) new Object[MAX_SIZE];
    }
    /*--------------------------------*/

    // 4. Methoden
    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(int position, T element) throws IllegalArgumentException {
        if (position < 0 || position > size+1) throw new IllegalArgumentException();

        if (position > this.maxSize) increaseSize();
        tArray[position] = element;
        size++;
    }

    @Override
    public void delete(int position) throws IllegalArgumentException {
        if (position < 0 || position > size+1) throw new IllegalArgumentException();
//        Gedanke ok, aber man muss hier aufpassen auf den Datentyp,
//        wir arbeiten komplett auf primitiven Datentyp, daher kann ich mein Ergebnis
//        welches so eine ArrayBaseListD ist, nicht einfach gleichsetzen mit dem primitiven Datentyp.
//        kurzgesagt, dies ist eine übersetzer klasse und ich kann nicht einfach
//        geschriebene methoden wiederverwerten, da sie sich
//        auf unterschiedliche  Objekttypen beziehen!!! daher geht folgendes NICHT
//        ILinearList result = new ArrayBasedListD();
//        result = extract(0,position-1);
//        result.concat(extract(position+1, size));

//        Geht daher anders, da das hier geführte primitive
//        Datentyp Array keine Mehtoden kennt


          for (int i=position; i < size; i++){
              tArray[i] = tArray[i+1];
          }
          tArray[size] = null;      // muss eigentlich nicht, da size mitgeführt wird,
                                    // und alles hinter size ja eh vergessen wird,
                                    // kann aber verwirren bei Ausgabe, daher hier null
          size--;
        decreaseSize();
    }

    @Override
    public T retrieve(int position) throws IllegalArgumentException {
        if (position < 0 || position > size) throw new IllegalArgumentException();
        return tArray[position];
    }

    @Override
    public void clean() {
        this.size = 0;
        this.maxSize = MAX_SIZE;
        // irgendwie logisch, aber schwer drauf zu kommen
        // warum muss MAX_SIZE in die Klammern und geht nicht,
        // wie oben bei der Initialisierung ohne?

//        this.tArray = new T[MAX_SIZE];              // geht bei int, String usw, aber nicht bei Generic
        //this.tArray = (T[]) new Object[MAX_SIZE];   // daher nutzt man diesen Ausdruck für Generics
        this.tArray = (T[]) new Object[MAX_SIZE];

        // da in diesem Fall Constructor und Clean die Instanzvariablen nur zurücksetzen, könnte man das
        // auch in eine private void init() auslagern.

    }


    @Override
    public void concat(ILinearList<T> toAddList) throws IllegalArgumentException {

    }

    @Override
    public ILinearList<T> extract(int fromPosition, int toPosition) {
        if (fromPosition < 0 || fromPosition > size) throw new IllegalArgumentException();
        if (toPosition < fromPosition || toPosition > size) throw new IllegalArgumentException();

        ILinearList result = new ArrayBasedListD();
        for (int i = fromPosition; i <= toPosition; i++){
            result.insert(i-fromPosition, this.tArray[i]);
        }
        ((ArrayBasedListD) result).decreaseSize();

        return result;
    }

    // 5. Hilfsmethoden
    private void increaseSize(){
        this.maxSize =this.maxSize*INCREASE_FACTOR;
    };

    private void decreaseSize(){
        if (this.maxSize > size* DECREASE_LIMIT_FACTOR){
            this.maxSize = this.maxSize*DECREASE_FACTOR;
            decreaseSize();
        }
    }

    // 6. MustHaveBlock, hash, toString, equals, meist autogenerated :)
}
