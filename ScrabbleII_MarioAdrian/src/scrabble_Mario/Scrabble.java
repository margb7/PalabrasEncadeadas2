package scrabble_Mario;

import java.util.Arrays;

/**
 * Clase para xestionar as regras do Scrabble e algunhas utilidades para as partidas
 * @author a21mariogb
 */
public class Scrabble {

    public static final int LON_MIN = 3;

    /**
     * Método para comprobar se a lonxitude dunha palabra é correcta.
     * @param palabra a palabra para comprobar.
     * @return
     */
    public static boolean lonxitudeCorrecta(String palabra) {
        return (palabra.length() >= LON_MIN);
    }

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
     * Método para obter a posición dunha casilla
     * @param cont
     * @return
     */
    public static int puntuacionCasilla(Casilla cont) {
        int puntos = 0;

        switch (cont.getContido().toLowerCase()){
            case "a":
            case "e":
            case "i":
            case "o":
            case "u":
            case "l":
            case "n":
            case "r":
            case "s":
            case "t":
                puntos = 1;
                break;
            case "d":
            case "g":
                puntos = 2;
                break;
            case "b":
            case "c":
            case "m":
            case "p":
                puntos = 3;
                break;
            case "f":
            case "h":
            case "v":
            case "w":
            case "y":
                puntos = 4;
                break;
            case "k":
                puntos = 5;
                break;
            case "j":
            case "x":
                puntos = 8;
                break;
            case "q":
            case "z":
            case "ñ":
                puntos = 10;
                break;
            case "ch":
                puntos = 5;
                break;
            case "ll":
            case "rr":
                puntos = 8;
                break;
        }

        return puntos;
    }

}