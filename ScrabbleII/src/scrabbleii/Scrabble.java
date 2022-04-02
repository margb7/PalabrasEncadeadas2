package scrabbleii;

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
    public static Posicion[] convertirEnPosicions(String palabra ) {
        Posicion[] lista = new Posicion[palabra.length()];
        int listIndex = 0, i = 0;
        char car;
    
        while(i < palabra.length()) {
    
            car = Character.toLowerCase(palabra.charAt(i));
    
            if(car == 'c') {
    
                if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'h') {
    
                    lista[listIndex++] = new Posicion("ch");
                    i++;
    
                } else {
    
                    lista[listIndex++] = new Posicion(Character.toString(car));
    
                }
    
            } else if(car == 'l') {
    
                if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'l') {
    
                    lista[listIndex++] = new Posicion("ll");
                    i++;
    
                } else {
    
                    lista[listIndex++] = new Posicion(Character.toString(car));
    
                }
    
            }else if(car == 'r') {
    
                if(i != palabra.length() - 1 && palabra.charAt(i + 1) == 'r') {
    
                    lista[listIndex++] = new Posicion("rr");
                    i++;
    
                } else {
    
                    lista[listIndex++] = new Posicion(Character.toString(car));
    
                }
    
            }  else {
    
                lista[listIndex++] = new Posicion(Character.toString(car)); 
    
            }
    
            i++; 
    
        }

        if(listIndex != i ) {

            lista = Arrays.copyOf(lista, listIndex );        // REVISAR ESTO

        }

        return lista;
    }

    /**
     * Método para obter a puntuación dunha palabra
     * @param palabra a palabra.
     * @return o número de puntos.
     */
    public static int obterPuntuacion(String palabra) {
        int puntuacion = 0;
        Posicion[] palabraPosicions = convertirEnPosicions(palabra);

        for (int i = 0; i < palabraPosicions.length; i++) {

            puntuacion += Scrabble.puntuacionPoscicion(palabraPosicions[i]);

        }

        return puntuacion;
    }

    /**
     * Método para obter a posición dunha casilla
     * @param cont
     * @return
     */
    public static int puntuacionPoscicion(Posicion cont) {
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