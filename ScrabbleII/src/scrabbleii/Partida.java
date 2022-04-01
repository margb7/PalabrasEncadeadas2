package scrabbleii;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase onde se realiza a partida 
 * @author a21mariogb
 */
public class Partida {
    
    private final int NUM_FILAS;
    private final float PROB_X2;
    private final float PROB_X3;
    private final float PROB_X4;

    private Posicion[][] taboleiro;
    private ArrayList<String> tablaLetras;
    private Xogador xogador1;
    private Xogador xogador2;
    private byte maxRendicions;
    private int numTurno;
    private int puntosVictoria;
    private boolean primerTurno;

    /**
     * Constructor para unha partida coas configuracións por defecto
     * @param xogador1 o xogador 1
     * @param xogador2 o xogador 2
     */
    public Partida(Xogador xogador1, Xogador xogador2) {

        this(xogador1, xogador2, 0.2F, 0.1F, 0.05F, 21);

    }

    /**
     * Constructor con algúns axustes da partida
     * @param xogador1 o xogador 1.
     * @param xogador2 o xogador 2.
     * @param probx2 a probabilidade de que aparezca un x2.
     * @param probx3 a probabilidade de que aparezca un x3.
     * @param probx4 a probabilidade de que aparezca un x4.
     * @param numFilas o número de filas do taboleiro.
     */
    public Partida(Xogador xogador1, Xogador xogador2, float probx2, float probx3, float probx4, int numFilas ) {

        this.xogador1 = xogador1;
        this.xogador2 = xogador2;

        // Axustes das regras da partida
        numTurno = 1;
        maxRendicions = 3;
        puntosVictoria = 45;

        // Axustes do taboleiro
        NUM_FILAS = numFilas;
        PROB_X2 = probx2;
        PROB_X3 = probx3;
        PROB_X4 = probx4;

        // Inicializar o taboleiro
        iniciarTaboleiro();
        engadirLetras();

    }

    /**
     * Getter para obter o xogador 1 da partida.
     * @return o xogador 1.
     */
    public Xogador getXogador1() {
        return xogador1;
    }

    /**
     * Getter para obter o xogador 2 da partida.
     * @return o xogador 2.
     */
    public Xogador getXogador2() {
        return xogador2;
    }

    /**
     * Getter para obter o máximo de rendicions ata perder na partida.
     * @return o número máximo de rendicions.
     */
    public byte getMaxRendicions() {
        return maxRendicions;
    }

    /**
     * Getter para obter o número de filas do taboleiro (coincide co número de columnas).  
     * @return o número de filas
     */
    public int getNUM_FILAS() {
        return NUM_FILAS;
    }

    /**
     * Getter para obter o número de puntos precisos para gañar a partida.
     * @return o número de puntos precisos para gañar
     */
    public int getPuntosVictoria() {
        return puntosVictoria;
    }

    /**
     * Setter para o xogador 1.
     * @param xogador1 o novo xogador.
     */
    public void setXogador1(Xogador xogador1) {
        this.xogador1 = xogador1;
    }

    /**
     * Setter para o xogador 2.
     * @param xogador2 o novo xogador.
     */
    public void setXogador2(Xogador xogador2) {
        this.xogador2 = xogador2;
    }

    /**
     * Settter para colocar o número máximo de veces que un xogador
     * pode rendirse ata perder.
     * @param maxRendicions o novo número de rendicións. Se é menor que 1 será 1.
     */
    public void setMaxRendicions(byte maxRendicions) {
        this.maxRendicions = (maxRendicions >= 1)? maxRendicions : 1;
    }

    /**
     * Setter para o número de puntos precisos para gañar a partida
     * @param puntosVictoria o novo número. Se é menor que 10 usarase 10.
     */
    public void setPuntosVictoria(int puntosVictoria) {
        this.puntosVictoria = (puntosVictoria >= 10)? puntosVictoria : 10;
    }

    /**
     * Método para inicializar as casillas do taboleiro cos multiplicadores e casillas vacías.
     */
    private void iniciarTaboleiro() {
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

    /**
     * Método para inicializar o arrayList das letras posibles para
     * repartir durante a partida aos xogadores.
     */
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

    /**
     * Método para repartir letras a un xogador. Ocúpase 
     * @param xog
     */
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
        boolean rematada = false;
        Xogador xogadorTurno;

        primerTurno = true;

        do {
            //seguinte turno
            xogadorTurno = seguinteXogador();

            if(tablaLetras.size() == 0 ) {

                rematada = true;

            } else {

                repartirLetras(xogadorTurno);
                xogarTurno(xogadorTurno);

                // final 

                if(tablaLetras.size() == 0 && xogadorTurno.getLetras().size() == 0 ) {

                    rematada = true;

                }

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
        boolean paso = false;
        byte comodinsNecesarios;
        byte fila, columna;
        String palabra;

        do {

            imprimirInformacionTurno(xogadorTurno);
            
            comodinsNecesarios = 0;
            correcto = true;
            palabra = EntradaSaida.lerString();
            
            if(palabra.equals("0") ) {

                xogadorTurno.engadirRendicion();
                paso = true;

            }

            // Comprobacións da palabra
            if(!paso ) {

                if(!Scrabble.lonxitudeCorrecta(palabra)) {

                    correcto = false;
    
                    EntradaSaida.imprimirErro("A palabra ten que ter como mínimo " + Scrabble.LON_MIN + " caracteres");
    
                } else {
    
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
                         
                        if(primerTurno )  {

                            if(comprobarForaBordes(palabra, horizontal, fila, columna) ) {

                                comodinsNecesarios = comprobarComodins(xogadorTurno, palabra, fila, columna, horizontal);
    
                                if(comodinsNecesarios == -1 ) {
                
                                    correcto = false;
                
                                } else {

                                    int puntos = colocarPalabra(Scrabble.convertirEnPosicions(palabra), fila, columna, horizontal);
        
                                    System.out.println("O xogador obtivo " + puntos + " puntos pola palabra");
                                    xogadorTurno.sumarPuntos(puntos);
                                    primerTurno = false;

                                }

                            } else {
        
                                EntradaSaida.imprimirErro("Non se pode colocar");
                                correcto = false;
        
                            }

                        } else {

                            if(podeColocarse(palabra, fila, columna, horizontal) ) {

                                comodinsNecesarios = comprobarComodins(xogadorTurno, palabra, fila, columna, horizontal);

                                if(comodinsNecesarios == -1 ) {
        
                                    correcto = false;
                
                                } else {

                                    int puntos = colocarPalabra(Scrabble.convertirEnPosicions(palabra), fila, columna, horizontal);
        
                                System.out.println("O xogador obtivo " + puntos + " puntos pola palabra");
                                xogadorTurno.sumarPuntos(puntos);

                                }

                            } else {
        
                                EntradaSaida.imprimirErro("Non se pode colocar");
                                correcto = false;
        
                            }

                            
                            
                        }
                        
                    }
    
                } 

            }
            

        } while(!correcto && !paso);

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

        out--;  // Xa que as posicións amosadas ao xogador comezan por índice 1

        return out;
    }

    private byte comprobarComodins(Xogador xog, String palabra, byte fila, byte columna, boolean horizontal) {

        byte out = xog.podeColocarPalabra(palabra, fila, columna, horizontal, taboleiro);

        System.out.println(xog.podeColocarPalabra(palabra, fila, columna, horizontal, taboleiro));

        if(out > xog.getComodins() ) {

            EntradaSaida.imprimirErro("Faltan comodíns ou letras para colocar esta palabra");
            out = -1;

        } else if(out != 0) {

            System.out.println("Queres usar " + out + " comodíns para colocar a palabra? (S/N)");

            if(!EntradaSaida.pedirConfirmacion() ) {

                out = -1;

            } 

        }

        return out;
    }

    private boolean comprobarForaBordes(String palabra, boolean horizontal, byte fila, byte columna ) {

        boolean out = true;

        if(horizontal ) {

            if((columna + palabra.length()) >= NUM_FILAS) {

                out = false;

            }

        } else {

            if((fila + palabra.length() ) >= NUM_FILAS) {

                out = false;

            }

        }

        return out;
    }

    private boolean podeColocarse(String palabra, byte fila, byte columna, boolean horizontal) {
        boolean out = true;
        byte numCoincidencias = 0;

        // Antes de comprobar se encaixa hai que comprobar que non se sae dos bordes 
        // do taboleiro
        out = comprobarForaBordes(palabra, horizontal, fila, columna); 

        if(out ) {

            numCoincidencias = obterNumeroCoincidencias(palabra, fila, columna, horizontal);

            out =  numCoincidencias != 0 && numCoincidencias != palabra.length();
        }

        // Non poden coincidir a palabra dada exactamente cunha palabra do taboleiro
        return out;
    }

    private byte obterNumeroCoincidencias(String palabra, byte fila, byte columna, boolean horizontal) {

        byte out = 0;

        Posicion[] palabraPosicions = Scrabble.convertirEnPosicions(palabra);
            String str;

        if(horizontal ) {

            for(int i = 0; i < palabra.length(); i++ ) {

                str = palabraPosicions[i].getContido();

                if(str.equals(taboleiro[fila][columna + i].getContido()) ) {

                    out++;

                } 

            }

        } else {

            for(int i = 0; i < palabra.length(); i++ ) {

                str = palabraPosicions[i].getContido();

                if(str.equals(taboleiro[fila + i][columna].getContido()) ) {

                    out++;

                } 

            }

        }

        return out;
    }

    /**
     * Método para colocar unha palabra no taboleiro. Devolve o número de puntos obtidos ao colocar a palabra
     * @param palabra a palabra a colocar
     * @param fila a fila na que colocar a palabra
     * @param columna a columna na que colocar a palabra
     * @param horizontal se é true colocase a palabra horizontalmente.
     * @return o número de puntos obtidos.
     */
    private int colocarPalabra(Posicion[] palabra, byte fila, byte columna, boolean horizontal) {

        int puntos = 0;

        if(horizontal ) {

            for(int i = 0; i < palabra.length; i++ ) {

                if(taboleiro[fila][columna + i].eMultiplicador() ) {

                    puntos += Scrabble.puntuacionPoscicion(palabra[i]) * taboleiro[fila][columna + i].getMultiplicador();

                } else {

                    puntos += Scrabble.puntuacionPoscicion(palabra[i]);

                }

                taboleiro[fila][columna + i] = palabra[i];

            }

        } else {

            for(int i = 0; i < palabra.length; i++ ) {

                if(taboleiro[fila + i][columna].eMultiplicador() ) {

                    puntos += Scrabble.puntuacionPoscicion(palabra[i]) * taboleiro[fila + i][columna].getMultiplicador();

                } else {

                    puntos += Scrabble.puntuacionPoscicion(palabra[i]);

                }

                taboleiro[fila + i][columna] = palabra[i];
            }

        }

        return puntos;
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

                System.out.print(" " + EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA) + " ");

            } else {

                System.out.print(EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA)+ " ");

            }

        }

        System.out.println("");

        for(int i = 0; i < NUM_FILAS; i++ ) {

            Posicion[] lista = taboleiro[i];

            if((i + 1) < 10 ) {

                System.out.print(" " + EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA) + " ");

            } else {

                System.out.print(EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA) + " ");

            }

            for(Posicion p : lista ) {

                if(p.eMultiplicador() ) {

                    System.out.print(EntradaSaida.stringColoreada(p.estadoPosicion(), EntradaSaida.CIAN) + " ");

                } else if(!p.estaBaleiro()) {

                    System.out.print(EntradaSaida.stringColoreada(p.estadoPosicion(), EntradaSaida.VERDE) + " ");

                } else {

                    System.out.print(EntradaSaida.stringColoreada(p.estadoPosicion(), EntradaSaida.CIAN) + " ");

                }

            }

            if((i + 1) < 10 ) {

                System.out.print(" " + EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA) + " ");

            } else {

                System.out.print(EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA) + " ");

            }

            System.out.println("");

        }

        System.out.print("   ");

        for(int i = 1; i <= NUM_FILAS; i++ ) {      // numeración das columnas

            if(i < 10 ) {

                System.out.print(" " + EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA) + " ");

            } else {

                System.out.print(EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA)+ " ");

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