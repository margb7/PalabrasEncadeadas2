package scrabbleii;

/**
 * Clase para xestionar as regras do Scrabble e algunhas utilidades para as partidas
 * @author a21mariogb
 */
public class Scrabble {

    public static final int LON_MIN = 3;

    public static boolean lonxitudeCorrecta(String palabra) {
        return (palabra.length() >= LON_MIN);
    }

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
    
            } else {
    
                lista[listIndex++] = new Posicion(Character.toString(car)); 
    
            }
    
            i++; 
    
        }
    
        return lista;
    }

    public static int obterPuntuacion(String palabra) {
        int puntuacion = 0;
        boolean caracterEspecial = false;

        for (int i = 0; i < palabra.length(); i++) {

            if(!caracterEspecial ) {

                if(palabra.charAt(i) == 'c' && i != palabra.length() && palabra.charAt(i + 1) == 'h') {

                    puntuacion += 67; // TODO: poner o valor correcto
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