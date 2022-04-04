package scrabble_Mario;


/**
 * Clase que contén a información de cada unha das casillas do taboleiro
 */
public class Casilla {

    private String contido;
    private byte multiplicador;


    /**
     * Constructor para unha casilla vacía.
     */
    public Casilla() {
        this("", (byte) 1);
    }

    /**
     * Constructor para unha casilla vacía ou con contido pero que non sexa unha
     * casilla especial
     * @param contido o contido da casilla
     */
    public Casilla(String contido ) {
        this(contido, (byte)1);
    }

    /**
     * Constructor principalmente usado para crear casillas especiais
     * @param contido 
     * @param multiplicador
     */
    public Casilla(String contido, byte multiplicador) {
        this.contido = contido;
        this.multiplicador = multiplicador;
    }

    /**
     * Getter do multiplicador da casilla.
     * @return o valor do multiplicador
     */
    public byte getMultiplicador() {
        return multiplicador;
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

    /**
     * Devolve a representación da casilla para mostrala no taboleiro.
     * @return <ul>
     *  <li>"  " se é unha casilla vacía</li>
     *  <li>"vv" se non é unha casilla vacía ou unha casilla especial (vv representa o valor da casilla)</li>
     *  <li>"x2", "x3", "x4" se é un multiplicador.</li> 
     * </ul>
     */
    public String valorMostra() {

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