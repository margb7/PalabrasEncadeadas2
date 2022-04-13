package scrabbleii_mario;

/**
 * Clase para xestionar as regras do Scrabble e algunhas utilidades para as partidas
 */
public class Scrabble {
    
    public static final int LON_MIN = 3;
    
    /**
     * Método para comprobar que a palabra cumpla coa lonxitude mínima dunha palabra.
     * @param palabra a palabra para comprobar.
     * @return true se a palabra é correcta.
     */
    public static boolean lonxitudeCorrecta(String palabra) {
        return (palabra.length() >= LON_MIN);
    }

    /**
     * Método para obter a posición dunha casilla.
     * @param cas a casilla para obter a puntuación.
     * @return a puntuación da casilla ou 0 se non se atopa na táboa de letras posibles.
     */
    public static int puntuacionCasilla(Casilla cas) {
        int puntos = 0;

        switch (cas.getContido().toLowerCase()){
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