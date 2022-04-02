package scrabbleii;

import java.util.ArrayList;

/**
 * Clase que contén utilidades para o programa
 */
public class Utilidades {
    
    /**
     * Método para copiar os elementos dun arraylist noutro. So copia a referencia.
     * @param arr o arrayList a copiar.
     * @return o novo arrayList.
     */
    public static ArrayList<String> copiarArray(ArrayList<String> arr ) {

        ArrayList<String> out = new ArrayList<>();

        for (String posicion : arr) {
            
            out.add(posicion);

        }

        return out;
    } 

}