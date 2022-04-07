/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabbleii_mario;

import java.util.ArrayList;

/**
 *
 * @author nocelo
 */
public class Xogador {
    private String nome;
    private int numPuntos;
    private ArrayList<String> letras;
    
    public Xogador(String nome) {
        this.nome = nome;
        letras = new ArrayList<>();
    }

    public ArrayList<String> getLetras() {
        return letras;
    }
    
    public String getNome() {
        return nome;
    }

    public int getPuntos() {
        return numPuntos;
    }

    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    public void aumentarPuntuacion(int puntos) {
        numPuntos += puntos;
    }

    public boolean podeColocarPalabra(String palabra) {
        boolean out = true;
        Casilla[] lista = Utilidades.convertirEnCasillas(palabra);
        boolean usouComodin = false;
        ArrayList<String> copia = Utilidades.copiarArray(letras);

        for(int i = 0; i < lista.length && out; i++ ) {

            if(!letras.contains(lista[i].getContido()) ) {

                if(!usouComodin && letras.contains("*") ) {

                    usouComodin = true;
                    copia.remove("*");

                } else {

                    out = false;

                }

            } else {

                copia.remove(lista[i].getContido());

            }

        }

        return out;
    }
}
