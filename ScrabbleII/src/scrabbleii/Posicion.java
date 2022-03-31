package scrabbleii;


/**
 * Clase que contén a información de cada unha das casillas do taboleiro
 */
public class Posicion {

    private String contido;
    private byte multiplicador;

    /**
     * Constructor para unha posición vacía ou con contido pero que non sexa unha
     * casilla especial
     * @param contido o contido da casilla
     */
    public Posicion(String contido ) {
        this(contido, (byte)1);
    }

    // TODO: Reorganizar (problema co significado de "contido")

    /**
     * Constructor principalmente usado para crear casillas especiais
     * @param contido 
     * @param multiplicador
     */
    public Posicion(String contido, byte multiplicador) {
        this.contido = contido;
        this.multiplicador = multiplicador;
    }

    /**
     * Getter do contido da casilla
     * @return o valor. Pode ser unha letra ou unha cadea vacía no caso de ser unha casilla vacía 
     * ou multiplicador.
     */
    public String getContido() {
        return contido;
    }

    /**
     * Método para comprobar se é unha casilla baleira
     * @return true se está baleira
     */
    public boolean estaBaleiro() {
        return contido.equals("");
    }

    /**
     * Método para comprobar se é un multiplicador.
     * @return true se é un multiplicador (o valor de multiplicador é maior que 1).
     */
    public boolean eMultiplicador() {
        return (multiplicador != 1);
    }

    // TODO: WTF es esto ---> debería ser toString probablemente

    /**
     * @return
     */
    public String estadoPosicion() {

        String out;

        if(estaBaleiro()) {

            if(eMultiplicador() ) {

                out = "x" + multiplicador;

            } else {

                out = "  ";

            }
            
        } else {

            if(contido.length() == 2 ) {

                out = contido;

            } else {

                out = " " + contido;

            }

        }

        return out;
    }

}