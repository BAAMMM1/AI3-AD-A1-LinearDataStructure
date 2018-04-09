package aufgabe1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shadai on 09.04.2018
 */
public class Main {
    public static void main(String[] args) {

        ILineareListe<Integer> meineIListe = new LineareListeKonkret<Integer>();
        meineIListe = null;
        System.out.println("meine liste = "+ meineIListe.concat(meineIListe));

        /*
        List<Integer> liste = new ArrayList<Integer>();
        liste = new LinkedList<Integer>();
        */

        System.out.println(meineIListe.numberOfElements());
    }
}
