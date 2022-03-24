package scrabbleii;

public class Posicion {

    private String contido;

    public Posicion(String contido) {

        this.contido = contido;

    }

    public String getContido() {
        return contido;
    }

    public boolean estaBaleiro() {
        
        return contido.equals("");

    }

    public int getPuntos() {

        

        return 0;

    }

}