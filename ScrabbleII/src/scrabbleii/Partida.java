package scrabbleii;

import java.util.Random;

/**
 * Clase onde se realiza a partida 
 * @author a21mariogb
 */
public class Partida {

    private final int NUM_FILAS = 21;

    private Posicion[][] taboleiro;
    private Xogador xogador1;
    private Xogador xogador2;
    private byte maxRendicions;
    private int numTurno;
    private int puntosVictoria;


    public Partida(Xogador xogador1, Xogador xogador2) {

        this.xogador1 = xogador1;
        this.xogador2 = xogador2;
        numTurno = 1;
        iniciarTaboleiro();
    
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



    /**
     * Método para inicializar as casillas do taboleiro 
     */
    public void iniciarTaboleiro() {
        Random rnd = new Random();

        taboleiro = new Posicion[NUM_FILAS][NUM_FILAS];

        for(int i = 0; i < NUM_FILAS; i++ ) {

            for(int j = 0; j < NUM_FILAS; j++ ) {



            }

        }
    }

    public void xogarPartida() {
        // reiniciarDatos();
        boolean rematada = false;
        boolean correcto;
        Xogador xogadorTurno;
        String palabra;

        do {
            //seguinte turno
            xogadorTurno = seguinteXogador();
            
            System.out.println("Turno de " + xogadorTurno.getNome());

            System.out.println("Introduce unha palabra cun mínimo de " + Scrabble.LON_MIN + " letras");

            // Palabra
            do {
                
                correcto = true;
                palabra = EntradaSaida.lerString();
                
                if(!Scrabble.lonxitudeCorrecta(palabra)) {

                    correcto = false;

                    EntradaSaida.imprimirErro("A palabra ten que ter como mínimo " + Scrabble.LON_MIN + " caracteres");

                }
                
                if(correcto ) {
/*
                    byte resultado = xogadorTurno.podeColocarPalabra(palabra, xogadorTurno.getComodins());

                    if(resultado == -1) {

                        correcto = false;
                        EntradaSaida.imprimirErro("O xogador non ten as letras ou comodíns para colocar a palabra. Volve a introducir unha palabra");
    
                    } else if(resultado != 0) {
    
                        System.out.println("Vanse a usar " + resultado + " comodíns para colocar a palabra");
    
                    }*/

                } 

            } while(!correcto);


            // Posición
            do {
                correcto = true;




            } while(!correcto);

            // final 
            if(xogadorTurno.getPuntos() >= puntosVictoria) {

                rematada = true;

            }

            if(xogadorTurno.getRendicion() == maxRendicions ) {

                rematada = true;

            }

        }while(!rematada);

        amosarResultados();

    }

    private void amosarResultados() {

        System.out.println("== RESULTADOS ==");

        if(xogador1.getRendicion() == maxRendicions ) {

            System.out.println("Gaña o xogador " + xogador2.getNome() + " xa que " + xogador1.getNome() + " pasou de turno " + maxRendicions + " veces");

        } else if (xogador2.getRendicion() == maxRendicions ) {

            System.out.println("Gaña o xogador " + xogador1.getNome() + " xa que " + xogador2.getNome() + " pasou de turno " + maxRendicions + " veces");

        } else {

            if(xogador1.getPuntos() > xogador2.getPuntos() ) {

                System.out.println("Gañou o xogador" + xogador1);
    
            } else if (xogador1.getPuntos() < xogador2.getPuntos() ) {
    
                System.out.println("Gañou o xogador " + xogador2);
    
            } else {
    
                System.out.println("EMPATE entre " + xogador1.getNome() + " e " + xogador2.getNome());
    
            }

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
