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

    /**
     * Getter do contido da casilla
     * @return o valor. Pode ser unha letra ou unha cadea vacía no caso de ser unha casilla vacía 
     * ou casilla especial.
     */
    public String getContido() {
        return contido;
    }

    /**
     * Getter do multiplicador da casilla.
     * @return o valor do multiplicador
     */
    public byte getMultiplicador() {
        
        byte out;

        if(tipo == 4) {

            out = 1;

        } else {

            out = (byte) (tipo + 1); 

        }

        return out;
    }

    /**
     * Getter para saber de que tipo é a casilla.
     * @return <ul>
     * <li>0 para unha casilla vacía</li>
     * <li>1 para unha casilla X2</li>
     * <li>2 para unha casilla X3</li>
     * <li>3 para unha casilla X4</li>
     * <li>4 para unha casilla de valor dobre de palabra</li>
     * </ul>
     */
    public byte getTipo() {
        return tipo;
    }

    /**
     * Método para saber se a casilla é especial.
     * @return true se se trata dunha casilla especial.
     */
    public boolean eEspecial() {
        return tipo != 0;
    }

    /**
     * Devolve a representación da casilla para mostrala no taboleiro.
     * @return <ul>
     *  <li>"  " se é unha casilla vacía</li>
     *  <li>"vv" se non é unha casilla vacía ou unha casilla especial (vv representa o valor da casilla)</li>
     *  <li>"x2", "x3", "x4" ou "2P" se é un multiplicador.</li> 
     * </ul>
     */
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
