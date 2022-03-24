package scrabbleii;

/**
 *
 * @author a21mariogb
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
        
        if (lon >= 2 && pal2.length() >= 2 ) {

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

    public static int obterPuntuacion(Posicion p, String c ) {
        int out = 0;

        

        return out;
    }

    public static int obterPuntuacion(String palabra) {
        int puntuacion = 0;
        boolean caracterEspecial = false;

        for (int i = 0; i < palabra.length(); i++) {

            if(!caracterEspecial ) {

                if(palabra.charAt(i) == 'c' && i != palabra.length() && palabra.charAt(i + 1) == 'h') {

                    puntuacion += 67; 
                    caracterEspecial = true;
    
                }

            } 

            puntuacion += Scrabble.puntuacionPoscicion(palabra.charAt(i) + "");

        }

        return puntuacion;
    }

    private static int puntuacionPoscicion(String cont) {
        int puntos = 0;

        switch (cont.toLowerCase()){
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
        }

        return puntos;
    }

}