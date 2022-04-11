package scrabbleii_mario;

import java.util.ArrayList;
import java.util.Arrays;

public class Utilidades {
    
    /**
     * Método para convertir en casillas unha palabra
     * @param palabra a palabra para convertir
     * @return o array de casillas da palabra
     */
    public static Casilla[] convertirEnCasillas(String palabra ) {
        Casilla[] lista = new Casilla[palabra.length()];
        int listIndex = 0, i = 0;
        char car;
    
        while(i < palabra.length()) {
    
            car = Character.toLowerCase(palabra.charAt(i));
    
            switch (car) {
                case 'c':
                    if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'h') {
                        
                        lista[listIndex++] = new Casilla("ch");
                        i++;
                        
                    } else {
                        
                        lista[listIndex++] = new Casilla(Character.toString(car));
                        
                    }   break;
                case 'l':
                    if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'l') {
                        
                        lista[listIndex++] = new Casilla("ll");
                        i++;
                        
                    } else {
                        
                        lista[listIndex++] = new Casilla(Character.toString(car));
                        
                    }   break;
                case 'r':
                    if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'r') {
                        
                        lista[listIndex++] = new Casilla("rr");
                        i++;
                        
                    } else {
                        
                        lista[listIndex++] = new Casilla(Character.toString(car));
                        
                    }   break;
                default:
                    lista[listIndex++] = new Casilla(Character.toString(car));
                    break;
            }
    
            i++; 
    
        }

        if(listIndex != i ) {

            lista = Arrays.copyOf(lista, listIndex );        

        }

        return lista;
    }

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
    
    /**
     * Método que ordena un array de xogadores en base ós puntos,
     * en orde ascendente
     * @param lista o array de xogadores a ordenar
     */
    public static void ordenarPorPuntos(Xogador[] lista ) {

        int numIntercambios = 0;
        boolean ordenado = false;
        Xogador aux;

        while (!ordenado) {

            for (int i = 0; i < lista.length - 1; i++) {

                if (lista[i].getPuntos() > lista[i + 1].getPuntos()) {

                    aux = lista[i];

                    lista[i] = lista[i + 1];
                    lista[i + 1] = aux;

                    numIntercambios++;

                }

            }

            if (numIntercambios == 0) { 

                ordenado = true;

            }

            numIntercambios = 0; 
        }
        
    }

}
