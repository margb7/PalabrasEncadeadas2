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
    private byte numPasos;
    private boolean activo;
    
    public Xogador(String nome) {
        this.nome = nome;
        activo = true;
        numPasos = 0;
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

    public byte getNumPasos() {
        return numPasos;
    }

    public boolean getEstado() {
        return activo;
    }

    public void setEstado(boolean activo) {
        this.activo = activo;
    }

    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    public void aumentarPasos() {
        numPasos++;
    } 

    public void aumentarPuntuacion(int puntos) {
        numPuntos += puntos;
    }

    public String toString() {
        return EntradaSaida.stringColoreada(nome, EntradaSaida.AZUL) + " con " + numPuntos + " puntos";
    }
}
