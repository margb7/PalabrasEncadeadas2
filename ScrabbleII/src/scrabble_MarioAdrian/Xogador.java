package scrabble_MarioAdrian;

import java.util.ArrayList;


/**
 * Clase coa información do xogador
 */
public class Xogador {
    
    private String nome;
    private ArrayList<String> letras;
    private byte numComodins;
    private byte pasos;
    private int puntos;

    /**
     * Constructor para crear un xogador a partir do seu nome. 
     * @param nome o nome do xogador.
     */
    public Xogador(String nome) {
        this.nome = nome;
        letras = new ArrayList<>();
        puntos = 0;
        pasos = 0;
        numComodins = 2;
    }

    /**
     * Getter para obter o nome do xogador.
     * @return o nome.
     */
    public String getNome() {
        return nome;
    } 

    /**
     * Getter para obter a puntuación do xogador.
     * @return a puntuación.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Getter para obter o número de pasos de turnos que realizou o xogador.
     * @return o numero de pasos.
     */
    public byte getRendicion() {
        return pasos;
    }

    /**
     * Getter para o número de comodíns que lle quedan ao xogador.
     * @return o número de comodíns
     */
    public byte getComodins() {
        return numComodins;
    }

    /**
     * Getter das letras que posúe o xogador para realizar unha xogada.
     * @return un arrayList das letras.
     */
    public ArrayList<String> getLetras() {
        return letras;
    }

    /**
     * Setter para cambiar o arrayList de letras do xogador.
     * @param letras o novo arrayList para o xogador.
     */
    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    /**
     * Setter para o numero de veces que o xogador pasou de turno
     * @param pasos o número de veces que pasou. Se é menor que 0 as veces 
     * que pasou pasarán a ser 0.
     */
    public void setRendicions(byte pasos ) {
        this.pasos = (pasos >= 0)? pasos : 0;
    }

    /**
     * Setter para colocar o número de puntos do xogador
     * @param puntos o número de puntos. Se é menor que 0 os puntos pasarán a ser 0.
     */
    public void setPuntos(int puntos) {
        this.puntos = (puntos >= 0)? puntos : 0;
    }

    /**
     * Método para restar ao xogador comodíns.
     * @param val o número de comodíns que se queren restar.
     */
    public void restarComodins(byte val ) {
        numComodins = (byte) (numComodins - val);
        numComodins = (numComodins >= 0)? numComodins : 0;
    }

    /**
     * Método para incrementar o número de veces que o xogador pasou de turno.
     */
    public void engadirRendicion() {
        this.pasos++;
    }

    /**
     * Método para sumar puntos ao xogador. Se os puntos agregados son menores que 0 os puntos do xogador
     * pasarán a ser 0.
     * @param puntos os puntos a sumar
     */
    public void sumarPuntos(int puntos ) {
        this.puntos = (puntos >= 0)? (this.puntos + puntos) : puntos;
    }

    /**
     * Devolve o número de comodins precisos para colocar a palabra.
     * @param palabra palabra a palabra a colocar
     * @return
     * <ul>
     *  <li><em>0</em>: se se pode colocar sen usar comodins</li>
     *  <li><em>num_comodins</em>: o numero de comodins que se precisan para colocar a palabra. O máximo valor é 
     *  lonxitude da palabra no caso de que o xogador non teña ningunha letra</li>
     * </ul>
    */
    public byte podeColocarPalabra(String palabra, byte fila, byte columna, boolean horizontal, Casilla[][] taboleiro) {
        byte out = 0;
        Casilla[] palabraConvertida = Scrabble.convertirEnPosicions(palabra);
        ArrayList<String> copiaLetras = Utilidades.copiarArray(letras);
        String str;
        boolean atopado;

        for(Casilla p : palabraConvertida ) {

            if(!copiaLetras.contains(p.getContido()) ) {

                atopado = false;

                for(int i = 0; i < palabra.length() && !atopado; i++ ) {
    
                    str = p.getContido();

                    if(horizontal ) {

                        if(str.equals(taboleiro[fila][columna + i].getContido()) ) {
    
                            atopado = true;
        
                        } 

                    } else {

                        if(str.equals(taboleiro[fila + i][columna].getContido()) ) {
    
                            atopado = true;
        
                        } 

                    }
    
                }

                if(!atopado ) {

                    out++;

                }
                
            } else {

                copiaLetras.remove(copiaLetras.indexOf(p.getContido()));

            }

        }

        return out;
    }

    @Override
    public String toString() {
        return nome + " con " + puntos + " puntos"; 
    }

}