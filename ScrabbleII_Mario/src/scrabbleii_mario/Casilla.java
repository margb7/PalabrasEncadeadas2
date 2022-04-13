package scrabbleii_mario;

/**
 * Clase que contén a información de cada unha das casillas do taboleiro
 */
public class Casilla {
    
    private String contido;
    private byte tipo;

    /**
     * Constructor para unha casilla dado un contido. Pode ser unha casilla vacía ou unha casilla con contido.
     * @param contido o contido da casilla.
     */
    public Casilla(String contido ) {
        this.contido = contido;
        tipo = 0;
    }

    /**
     * Constructor para unha casilla para casillas especiais
     * @param tipo
     */
    public Casilla(byte tipo ) {
        contido = "";
        this.tipo = tipo;
    }

    public String getContido() {
        return contido;
    }

    public byte getMultiplicador() {
        
        byte out;

        if(tipo == 4) {

            out = 1;

        } else {

            out = (byte) (tipo + 1); 

        }

        return out;
    }

    public byte getTipo() {
        return tipo;
    }

    public boolean eEspecial() {
        return tipo != 0;
    }

    public boolean estaVacia() {
        return contido.equals("") && tipo == 0;
    }

    public String valorMostra() {
        String out = "";

        switch(tipo) {

            case 0:

                if(contido.equals("") ) {
                    
                    out = "  ";

                } else {

                    if(contido.length() == 1 ) {

                        out = " " + contido;

                    } else {

                        out = contido;

                    }

                }

                break;
            case 1:
                out = EntradaSaida.stringColoreada("X2", EntradaSaida.VERDE);;
                break;
            case 2:
                out = EntradaSaida.stringColoreada("X3", EntradaSaida.VERDE);;
                break;
            case 3:
                out = EntradaSaida.stringColoreada("X4", EntradaSaida.VERDE);
                break;
            case 4:
                out = EntradaSaida.stringColoreada("2P", EntradaSaida.VERDE);;
                break;
        }

        return out;
    }

}
