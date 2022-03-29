package scrabbleii;

public class Posicion {

    private String contido;
    private byte multiplicador;

    public Posicion(String contido ) {
        this(contido, (byte)1);
    }

    public Posicion(String contido, byte multiplicador) {
        this.contido = contido;
        this.multiplicador = multiplicador;
    }

    public String getContido() {
        return contido;
    }

    public boolean estaBaleiro() {
        return contido.equals("");
    }

    public boolean eMultiplicador() {
        return (multiplicador != 1);
    }

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