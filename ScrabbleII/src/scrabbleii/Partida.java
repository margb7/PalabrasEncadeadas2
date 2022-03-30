package scrabbleii;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase onde se realiza a partida 
 * @author a21mariogb
 */
public class Partida {

    private final int NUM_FILAS = 21;
    private final float PROB_X2 = 0.2F;
    private final float PROB_X3 = 0.1F;
    private final float PROB_X4 = 0.05F;

    private Posicion[][] taboleiro;
    private ArrayList<String> tablaLetras;
    private Xogador xogador1;
    private Xogador xogador2;
    private byte maxRendicions;
    private int numTurno;
    private int puntosVictoria;


    public Partida(Xogador xogador1, Xogador xogador2) {

        this.xogador1 = xogador1;
        this.xogador2 = xogador2;
        numTurno = 1;
        maxRendicions = 3;
        puntosVictoria = 45;
        iniciarTaboleiro();
        engadirLetras();

    }

    public Xogador getXogador1() {
        return xogador1;
    }

    public Xogador getXogador2() {
        return xogador2;
    }

    /**
     * Método para inicializar as casillas do taboleiro 
     */
    public void iniciarTaboleiro() {
        Random rnd = new Random();
        float num;

        taboleiro = new Posicion[NUM_FILAS][NUM_FILAS];

        for(int i = 0; i < NUM_FILAS; i++ ) {

            for(int j = 0; j < NUM_FILAS; j++ ) {

                num = rnd.nextFloat();

                if(num < PROB_X2 ) {

                    taboleiro[i][j] = new Posicion("", (byte) 2);

                } else if(num < PROB_X3 ) {

                    taboleiro[i][j] = new Posicion("", (byte) 3);

                } else if(num < PROB_X4) {
                
                    taboleiro[i][j] = new Posicion("", (byte) 4);

                }else {

                    taboleiro[i][j] = new Posicion("", (byte) 1);

                }

            }

        }
    }

    private void engadirLetras() {

        String[] letras = new String[]{"A","A","A","A","A","A","A","A","A","A","A",
                                   "A","A","A","B","B","B","C","C","C","C","C",
                                  "CH","D","D","D","D","D","D","E","E","E","E",
                                   "E","E","E","E","E","E","E","E","E","F","F",
                                   "G","G","H","H","I","I","I","I","I","I","I",
                                   "J","L","L","L","L","L","L","LL","M","M","M",
                                   "M","N","N","N","N","N","N","Ñ","O","O","O",
                                   "O","O","O","O","O","O","O","P","P","P","Q",
                                   "R","R","R","R","R","R","RR","S","S","S","S",
                                   "S","S","S","T","T","T","T","T","U","U","U",
                                   "U","U","U","U","V","V","X","Y","Z"   
                                };

        tablaLetras = new ArrayList<String>();

        for(String s : letras ) {

            tablaLetras.add(s);

        }

    }

    private void repartirLetras(Xogador xog) {

        if(xog.getLetras().size() < 7 ) {

            Random rnd = new Random();
            int letrasFaltan = 7 - xog.getLetras().size();
            int num;

            if(letrasFaltan > tablaLetras.size() ) {

                letrasFaltan = tablaLetras.size();

            }

            for(int i = 0; i < letrasFaltan; i++ ) {

                num = rnd.nextInt(tablaLetras.size());

                xog.getLetras().add(tablaLetras.get(num).toLowerCase());
                tablaLetras.remove(num);
                
            }

        }

    }

    public void xogarPartida() {
        // reiniciarDatos();
        boolean rematada = false;
        Xogador xogadorTurno;

        do {
            //seguinte turno
            xogadorTurno = seguinteXogador();

            if(tablaLetras.size() == 0 ) {

                rematada = true;

            } else {

                repartirLetras(xogadorTurno);
                xogarTurno(xogadorTurno);

                // final 
                if(xogadorTurno.getPuntos() >= puntosVictoria) {

                    rematada = true;

                }

                if(xogadorTurno.getRendicion() == maxRendicions ) {

                    rematada = true;

                }

            }

        }while(!rematada);

        amosarResultados();

    }

    private void xogarTurno(Xogador xogadorTurno) {

        boolean correcto;
        boolean horizontal;
        byte comodinsNecesarios;
        byte fila, columna;
        String palabra;

        do {

            imprimirInformacionTurno(xogadorTurno);
            
            comodinsNecesarios = 0;
            correcto = true;
            palabra = EntradaSaida.lerString();
            
            if(palabra.equals("0") ) {

                xogadorTurno.setRendicions((byte) (xogadorTurno.getRendicion() + 1) );
                correcto = false;

            }

            // Comprobacións da palabra

            if(correcto && !Scrabble.lonxitudeCorrecta(palabra)) {

                correcto = false;

                EntradaSaida.imprimirErro("A palabra ten que ter como mínimo " + Scrabble.LON_MIN + " caracteres");

            } else {

                comodinsNecesarios = xogadorTurno.podeColocarPalabra(palabra);

                if(comodinsNecesarios > xogadorTurno.getComodins() ) {

                    EntradaSaida.imprimirErro("Faltan comodíns ou letras para colocar esta palabra");

                    correcto = false;

                } else if(comodinsNecesarios != 0) {

                    System.out.println("Queres usar " + comodinsNecesarios + " comodíns para colocar a palabra? (S/N)");

                    correcto = EntradaSaida.pedirConfirmacion();

                }

                if(correcto ) {

                    char op;

                    // Posición da palabra

                    System.out.println("Introduce o número de fila");
                    fila = pedirPosicion();

                    System.out.println("Introduce o número de columna");
                    columna = pedirPosicion();

                    System.out.println("Colocar horizontalmente ou verticalmente(H/V)");
                    
                    do {
                        op = Character.toLowerCase(EntradaSaida.lerChar());

                        if(op != 'h' && op != 'v' ) {

                            EntradaSaida.imprimirErro("Ten que ser 'v' ou 'h'");

                        }

                    } while(op != 'h' && op != 'v');

                    if(op == 'h' ) {

                        horizontal = true;

                    } else {

                        horizontal = false;

                    }

                    if(horizontal ) {

                        if(fila + palabra.length() > NUM_FILAS) {

                            correcto = false;

                        }

                    } else {

                        if(columna + palabra.length() > NUM_FILAS) {

                            correcto = false;

                        }

                    }

                    if(correcto && podeColocarse(Scrabble.convertirEnPosicions(palabra), fila, columna, horizontal)) {

                        System.out.println("Podese colocar");

                    } else {

                        EntradaSaida.imprimirErro("Non se pode colocar");

                    }
                    
                }

            } 

        } while(!correcto);

    }

    private byte pedirPosicion() {
        byte out = 0;
        boolean correcto;

        do {
            correcto = true;

            out = EntradaSaida.lerByte();

            if(out < 0 || out > NUM_FILAS ) {

                EntradaSaida.imprimirErro("Ten que ser un valor de 0 a" + NUM_FILAS);
                correcto = false;

            }

        }while(!correcto);

        return out;
    }

    private boolean podeColocarse(Posicion[] palabra, byte fila, byte columna, boolean horizontal) {
        boolean out = true;
        byte x, y;
        byte numCoincidencias = 0;

        for(int i = 0; i < palabra.length; i++ ) {



        }


        return numCoincidencias != 0 && numCoincidencias != palabra.length;
    }

    private boolean colocarPalabra() {

        return false;

    }

    private void imprimirInformacionTurno(Xogador xogador ) {

        System.out.println("_______________________");
        System.out.println("#TABOLEIRO#");
        System.out.println("");

        imprimirTaboleiro();

        System.out.println("_______________________");
        System.out.println("Turno de " + xogador.getNome());
        System.out.println("Letras : " + xogador.getLetras());
        System.out.println("Introduce unha palabra cun mínimo de " + Scrabble.LON_MIN + " letras ou \"0\" para rendirte");

    }

    private void imprimirTaboleiro() {

        System.out.print("   ");

        for(int i = 1; i <= NUM_FILAS; i++ ) {      // numeración das columnas

            if(i < 10 ) {

                System.out.print(" " + i + " ");

            } else {

                System.out.print(i + " ");

            }

        }

        System.out.println("");

        for(int i = 0; i < NUM_FILAS; i++ ) {

            Posicion[] lista = taboleiro[i];

            if((i + 1) < 10 ) {

                System.out.print(" " + (i + 1) + " ");

            } else {

                System.out.print(i + 1 + " ");

            }

            for(Posicion p : lista ) {

                System.out.print(p.estadoPosicion() + " ");

            }

            System.out.println("");

        }

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
    
                System.out.println("Empate entre " + xogador1.getNome() + " e " + xogador2.getNome());
    
            }

        }

        System.out.println("");
    }

}