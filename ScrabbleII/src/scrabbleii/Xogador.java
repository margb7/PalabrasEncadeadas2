package scrabbleii;

import java.util.ArrayList;

public class Xogador {
    
    private String nome;
    private ArrayList<String> letras;
    private byte numComodins;
    private byte pasos;
    private int puntos;

    public Xogador(String nome) {

        this.nome = nome;
        letras = new ArrayList<>();
        puntos = 0;
        pasos = 0;
        numComodins = 2;

    }

    public String getNome() {
        return nome;
    } 

    public int getPuntos() {
        return puntos;
    }

    public byte getRendicion() {
        return pasos;
    }

    public byte getComodins() {
        return numComodins;
    }

    public ArrayList<String> getLetras() {
        return letras;
    }

    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    public void setRendicions(byte pasos ) {
        this.pasos = (pasos >= 0)? pasos : 0;
    }

    public void setPuntos(int puntos) {
        this.puntos = (puntos >= 0)? puntos : 0;
    }

    public void sumarPuntos(int puntos ) {
        this.puntos = (puntos >= 0)? (this.puntos + puntos) : puntos;
    }

    /**
     * Método para saber se o xogador pode colocar a palabra. Devolve o número de comodins precisos para colocar a palabra
     * @param palabra palabra a palabra a colocar
     * @return devolve 
     * <ul>
     *  <li><em>-1</em>: se non se pode colocar</li>
     *  <li><em>0</em>: se se pode colocar sen usar comodins</li>
     *  <li><em>num_comodins</em>: o numero de comodins que se precisan para colocar a palabra. O máximo valor é 
     *  lonxitude da palabra no caso de que o xogador non teña ningunha letra</li>
     * </ul>
    */
    //@SuppressWarnings("unchecked")
    public byte podeColocarPalabra(String palabra) {
        byte out = 0;
        Posicion[] palabraConvertida = Scrabble.convertirEnPosicions(palabra);
        ArrayList<String> copiaLetras = (ArrayList<String>) letras.clone();

        for(Posicion p : palabraConvertida ) {

            if(!copiaLetras.contains(p.getContido()) ) {

                out++;
                
            } else {

                copiaLetras.remove(copiaLetras.indexOf(p.getContido()));

            }

        }

        // TODO: implementar checks por si el array de letras está vacío

        return out;
    }

    @Override
    public String toString() {
        return nome + " con " + puntos + " puntos"; 
    }

}
