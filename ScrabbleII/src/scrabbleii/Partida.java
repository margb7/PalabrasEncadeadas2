/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabbleii;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author a21mariogb
 */
public class Partida {

    private ArrayList<Posicion> taboleiro;
    private Xogador xogador1;
    private Xogador xogador2;
    private int numTurno;
    private int puntosVictoria;


    public Partida(Xogador xogador1, Xogador xogador2) {

        this.xogador1 = xogador1;
        this.xogador2 = xogador2;
        taboleiro = new ArrayList<>();
        numTurno = 1;
    
    }

    public Xogador getXogador1() {
        return xogador1;
    }

    public Xogador getXogador2() {
        return xogador2;
    }

    /*public void realizarXogada(String palabra) {
        int puntacionTemp = Scrabble.obterPuntuacionPalabra(palabra);
        int puntuacionTotalXog;

        if (xogadorActual == 1) {
            xogador1.aumentarPuntuacion(puntacionTemp);
            puntuacionTotalXog = xogador1.getPuntuacion();
        } else {
            xogador2.aumentarPuntuacion(puntacionTemp);
            puntuacionTotalXog = xogador2.getPuntuacion();
        }
        System.out.print("\tPunt. palabra: " + puntacionTemp);
        System.out.println("\tPUNT. TOTAL: " + puntuacionTotalXog);

        xogadorActual = (xogadorActual == 1)?2:1; //Cambiamos de xogador
    }*/
/*
    public void mostrarResultados() {
        int puntuacionXog1 = xogador1.getPuntuacion();
        int puntuacionXog2 = xogador2.getPuntuacion();

        System.out.println("\n ****   RESULTADO FINAL    ****");
        if (puntuacionXog1 == puntuacionXog2) {
            System.out.println("Os xogadores EMPATARON con " + puntuacionXog1 + " puntos");
        } else if (puntuacionXog1 > puntuacionXog2) {
            System.out.println("O xogador 1 GAÑOU con " + puntuacionXog1 + " puntos, sobre os " + puntuacionXog2 + " puntos do xogador 2");
        } else {
            System.out.println("O xogador 2 GAÑOU con " + puntuacionXog2 + " puntos, sobre os " + puntuacionXog1 + " puntos do xogador 1");
        }
    }
*/
    /**
     * Método para inicializar as casillas do taboleiro 
     */
    public void iniciarTaboleiro() {
        Random rnd = new Random();

        for(int i = 0; i < 21; i++ ) {

            for(int j = 0; j < 21; j++ ) {



            }

        }
    }

    public void xogarPartida() {
        // reiniciarDatos();
        boolean rematada = false, correcto;
        Xogador xogadorTurno;

        do {
            //seguinte turno
            xogadorTurno = seguinteXogador();
            
            do {
                


                correcto = true;

            } while(!correcto);

            // final 
            if(xogadorTurno.getPuntos() >= puntosVictoria) {



            }

        }while(!rematada);
        amosarResultados();

    }

    private void amosarResultados() {

        System.out.println("== RESULTADOS ==");

        if(xogador1.getPuntos() > xogador2.getPuntos() ) {

            System.out.println("Gañou o xogador" + xogador1);

        } else if (xogador1.getPuntos() < xogador2.getPuntos() ) {

            System.out.println("Gañou o xogador " + xogador2);

        } else {

            System.out.println("EMPATE entre " + xogador1.getNome() + " e " + xogador2.getNome());

        }

        System.out.println("");
    }

    private Xogador seguinteXogador() {
        Xogador out;
        
        if(numTurno == 1 ) {

            out = xogador1;
            numTurno++;

        } else {

            out = xogador2;
            numTurno--;

        }

        return out;
    }

}
