package scrabbleii_mario;

/**
 *
 * @author 
 */
public class Scrabble {
    
    public static final int LON_MIN = 3;
    
    public static boolean comprobarSaida(String palabra) {
        return (palabra.equals("0"));
    }
    
    public static boolean lonxitudeCorrecta(String palabra) {
        return (palabra.length() >= LON_MIN);
    }
    
    /**
     * 
     * @param pal1
     * @param pal2
     * @return O resultado é un byte cos seguintes valores
     * <ul>
     *  <li> 0: se as dúas palabras teñen mínimo 2 caracteres e os últimos 2 caracteres
     * da primeira palabra coinciden cos 2 primeiros caracteres da segunda palabra</li>
     *  <li> 1: se as dúas palabras teñen mínimo 2 caracteres e os últimos 2 caracteres
     * da primeira palabra NON coinciden cos 2 primeiros caracteres da segunda palabra</li>
     *  <li> -1: se algunha das dúas palabras non ten unha lonxitude mínima de 2 caracteres</li>
     * </ul>
     */
    public static byte comprobarPalabra(String pal1, String pal2) {
        byte resultado = 0;
        int lon = pal1.length();
        if (lon >= 2 && pal2.length() >= 2) {
            if (pal1.substring(lon-2, lon).equals(pal2.substring(0, 2))) {
                resultado = 0;
            } else {
                resultado = 1;
            }
        } else {
            resultado = -1;
        }
        
        return resultado;
    }
    
    public static int obterPuntuacionPalabra(String palabra) {
        int puntuacion = 0;
       
        for (int i = 0; i < palabra.length(); i++) {
            puntuacion += Scrabble.obterPuntuacionLetra(palabra.charAt(i));
        }
        return puntuacion;
    }
    
    private static int obterPuntuacionLetra(char car) {
        int puntos = 0;
        
        switch (Character.toUpperCase(car)){
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'L':
            case 'N':
            case 'R':
            case 'S':
            case 'T':
                puntos = 1;
                break;
            case 'D':
            case 'G':
                puntos = 2;
                break;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                puntos = 3;
                break;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                puntos = 4;
                break;
            case 'K':
                puntos = 5;
                break;
            case 'J':
            case 'X':
                puntos = 8;
                break;
            case 'Q':
            case 'Z':
            case 'Ñ':
                puntos = 10;
                break;
        }
        
        return puntos;
    }
    
}
