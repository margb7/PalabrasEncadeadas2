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
     *  TODO: el uso de comodines después del ch / ll
     *  mejor pasar directamente usando c y despues comprobar el siguiente indice
     * @param palabra
     * @return
     */
    public boolean podeColocarPalabra(String palabra, byte numComodins ) {
        boolean out = true;
        int i = 0;
        boolean comprobarCH = false;
        boolean comprobarLL = false;
        ArrayList<String> letrasXogador = (ArrayList<String>) letras.clone();

        do {

            if(comprobarLL) {

                if(palabra.charAt(i) == 'h' ) {

                    out = letrasXogador.contains("ch");

                } else {

                    if(letrasXogador.contains("c") ) {

                        out = letrasXogador.contains("" + palabra.charAt(i));

                    } else {

                        out = false;

                    }

                }

            } else if(comprobarCH) {

                if(palabra.charAt(i) == 'l' ) {

                    out = letrasXogador.contains("l");

                } else {

                    if(letrasXogador.contains("l") ) {

                        out = letrasXogador.contains(palabra.charAt(i));

                    } else {

                        out = false;

                    }

                }

            } else if(palabra.charAt(i) == 'c' && i != palabra.length() - 1 ) {

                comprobarCH = true;

            } else if(palabra.charAt(i) == 'l' && i != palabra.length() - 1 ) {

                comprobarLL = true;

            } else {

                out = letrasXogador.contains("" + palabra.charAt(i));

            }
            
            if(!out) {

                numComodins--;

                if(numComodins >= 0 ) {

                    out = true;

                }

            }

            i++;

        } while(i < palabra.length() && out);
        

        return out;
    }

    @Override
    public String toString() {
        return nome + " con " + puntos + " puntos"; 
    }

}
