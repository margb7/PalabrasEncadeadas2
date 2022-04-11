package scrabbleii_mario;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author a21mariogb
 */
public class Xogo {
    
    private final int NUM_FILAS;
    private final boolean MODO_NORMAL;

    private Casilla[][] taboleiro;
    private ArrayList<String> tablaLetras;
    private Xogador[] xogadores;
    private byte maxRendicions;
    private int numTurno;
    private int puntosVictoria;
    private boolean primerTurno;
    
    public Xogo(Xogador[] xogadores) {
        this.xogadores = xogadores;
        numTurno = 0;

        NUM_FILAS = 21;
        MODO_NORMAL = true;
        iniciarTaboleiro(5, 4, 3, 2);
        engadirLetras();

    }

    public Xogo(Xogador[] xogadores, int numX2, int numX3, int numX4, int numX2Pal, int puntosVictoria, byte maxRendicions ,int numFilas ) {
        this.xogadores = xogadores;
        numTurno = 0;

        this.puntosVictoria = puntosVictoria;
        this.maxRendicions = maxRendicions;

        NUM_FILAS = numFilas;
        MODO_NORMAL = false;
        iniciarTaboleiro(numX2, numX3, numX4, numX2Pal);
        engadirLetras();
    }

    private void iniciarTaboleiro(int numX2, int numX3, int numX4, int numX2Pal) {
    
        taboleiro = new Casilla[NUM_FILAS][NUM_FILAS];

        for (int i = 0; i < NUM_FILAS; i++) {

            for (int j = 0; j < NUM_FILAS; j++) {

                taboleiro[i][j] = new Casilla("");

            }

        }

        colocarCasillaEspecial((byte)1, numX2);
        colocarCasillaEspecial((byte)2, numX3);
        colocarCasillaEspecial((byte)3, numX4);
        colocarCasillaEspecial((byte)4, numX2Pal);
    }

    private void colocarCasillaEspecial(byte tipo, int cantidade ) {
        Random rnd = new Random();
        int px, py;

        do {

            px = rnd.nextInt(NUM_FILAS);
            py = rnd.nextInt(NUM_FILAS);

            if(!taboleiro[px][py].eEspecial() ) {

                taboleiro[px][py] = new Casilla(tipo);
                cantidade--;

            }


        } while(cantidade > 0);

    }

    /**
     * Método para inicializar o arrayList das letras posibles para
     * repartir durante a partida aos xogadores.
     */
    private void engadirLetras() {

        String[] letras = new String[] {    "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
                                            "A", "A", "A", "B", "B", "B", "C", "C", "C", "C", "C",
                                            "CH", "D", "D", "D", "D", "D", "D", "E", "E", "E", "E",
                                            "E", "E", "E", "E", "E", "E", "E", "E", "E", "F", "F",
                                            "G", "G", "H", "H", "I", "I", "I", "I", "I", "I", "I",
                                            "J", "L", "L", "L", "L", "L", "L", "LL", "M", "M", "M",
                                            "M", "N", "N", "N", "N", "N", "N", "Ñ", "O", "O", "O",
                                            "O", "O", "O", "O", "O", "O", "O", "P", "P", "P", "Q",
                                            "R", "R", "R", "R", "R", "R", "RR", "S", "S", "S", "S",
                                            "S", "S", "S", "T", "T", "T", "T", "T", "U", "U", "U",
                                            "U", "U", "U", "U", "V", "V", "X", "Y", "Z", "*", "*" };

        tablaLetras = new ArrayList<>();

        for (String s : letras) {

            tablaLetras.add(s);

        }

    }


    public void xogarPartida() {

        boolean rematada = true;
        Xogador xogadorTurno;

        for(Xogador x : xogadores ) {

            repartirLetras(x);

        }

        primerTurno = true;

        do {

            xogadorTurno = seguinteXogador();

            xogarTurno(xogadorTurno);

        } while(!rematada);

        mostrarResultados();

    } 
    
    /**
     * Método que devolve o seguinte xogador do turno.
     * @return o xogador.
     */
    private Xogador seguinteXogador() {
        Xogador out;

        out = xogadores[numTurno];
        numTurno++;

        if(numTurno > xogadores.length ) {

            numTurno = 0;

        }

        return out;
    }
    
    private void xogarTurno(Xogador xogadorTurno) {

        boolean correcto;
        boolean paso = false;
        boolean horizontal;
        byte fila, columna;     // Onde colocar a palabra
        String palabra; 

        do {

            correcto = true;
            imprimirInfoTurno();
            
            System.out.println("Introduce unha palabra cunha lonxitude mínima de " + Scrabble.LON_MIN + " letras ou \"0\" para pasar de turno");

            do {
                
                correcto = true;

                palabra = EntradaSaida.lerString();

                if(palabra.equals("0") ) {

                    paso = true;

                } else if(!Scrabble.lonxitudeCorrecta(palabra) ) {

                    correcto = false;
                    EntradaSaida.imprimirErro("Ten que ter como mínimo " + Scrabble.LON_MIN + " letras de lonxitude");

                } 

            } while(!correcto && !paso );

            if(!paso ) {

                if(!primerTurno ) {

                    System.out.println("Introduce o número de fila onde colocar a palabra");
                    fila = (byte) EntradaSaida.pedirRango(1, NUM_FILAS);

                    System.out.println("Introduce o número de columna onde colocar a palabra");
                    columna = (byte) EntradaSaida.pedirRango(1, NUM_FILAS);

                    fila--;
                    columna--;

                } else {

                    fila = (byte) (NUM_FILAS / 2);
                    columna = (byte) (NUM_FILAS / 2);

                    System.out.println("Ao ser o primer turno a palabra colocarase na fila " + (fila + 1) + " e columna " + (columna + 1));

                }

                horizontal = pedirHorizontal();

                if(podeColocarPalabra(palabra, fila, columna, horizontal, xogadorTurno) ) {

                    // Colocar palabra

                    colocarPalabra(palabra, fila, columna, horizontal, xogadorTurno);

                } else {

                    correcto = false;

                }

            } else {    // Xogador pasou de turno 

                xogadorTurno.aumentarPasos();

            }

        } while(!correcto && !paso);

    }

    private boolean podeColocarPalabra(String palabra, byte fila, byte columna, boolean horizontal, Xogador xog ) {
        boolean out = true;
        boolean usouComodin = false;
        byte numCoincidencias = 0;
        Casilla[] convertida = Utilidades.convertirEnCasillas(palabra);
        ArrayList<String> letrasXogador = Utilidades.copiarArray(xog.getLetras());
        
        out = comprobarForaBordes(convertida.length, horizontal, fila, columna);

        if(out ) {

            Casilla pezaTaboleiro;

            for(int i = 0; i < convertida.length; i++ ) {

                if(horizontal ) {

                    pezaTaboleiro = taboleiro[fila + i][columna];

                } else {

                    pezaTaboleiro = taboleiro[fila][columna + i];

                }

                if(pezaTaboleiro.getContido().equals(convertida[i].getContido()) ) {

                    numCoincidencias++;

                } else {

                    if(letrasXogador.contains(convertida[i].getContido()) ) {

                        letrasXogador.remove(convertida[i].getContido());
    
                    } else {

                        if(letrasXogador.contains("*") ) {

                            if(!usouComodin ) {

                                letrasXogador.remove("*");
                                usouComodin = true;

                            } else {

                                out = false;

                            }
    
                        } else {

                            out = false;

                        }

                    }

                }

            }

            if(out ) {

                if(numCoincidencias == 0 && !primerTurno) {

                    EntradaSaida.imprimirErro("Non encaixa con ningunha letra do taboleiro");
                    out = false;
        
                } else if(numCoincidencias == convertida.length ) {
        
                    EntradaSaida.imprimirErro("Non pode coincidir exactamente con letras xa colocadadas no taboleiro");
                    out = false;
        
                }

            } else {

                EntradaSaida.imprimirErro("Non tes as letras ou comodíns suficientes para colocar a palabra");

            }

        } else {

            EntradaSaida.imprimirErro("A palabra vaise fora dos bordes");

        }

        return out;
    }

    private int colocarPalabra(String palabra, byte fila, byte columna, boolean horizontal, Xogador xog ) {
        int out = 0;


        return out;
    }

    private boolean comprobarForaBordes(int lonxitude, boolean horizontal, byte fila, byte columna) {

        boolean out = true;

        if (horizontal) {

            if ((columna + lonxitude) >= NUM_FILAS) {

                out = false;

            }

        } else {

            if ((fila + lonxitude) >= NUM_FILAS) {

                out = false;

            }

        }

        return out;
    }

    private boolean pedirHorizontal() {
        char op;

        System.out.println("Colocar horizontalmente ou verticalmente(H/V)");

        do {

            op = Character.toLowerCase(EntradaSaida.lerChar());

            if (op != 'h' && op != 'v') {

                EntradaSaida.imprimirErro("Ten que ser 'v' ou 'h'");

            }

        } while (op != 'h' && op != 'v');

        return op == 'h';
    }
    
    private void mostrarResultados() {

        System.out.println("RESULTADOS");
        
    }

    private void repartirLetras(Xogador x ) {

        if (x.getLetras().size() < 7) {

            Random rnd = new Random();
            int letrasFaltan = 7 - x.getLetras().size();
            int num;

            if (letrasFaltan > tablaLetras.size()) { // Se non quedan suficientes letras para repartir

                letrasFaltan = tablaLetras.size(); // Repartir as que quedan -> se non queda non entra no bucle for

            }

            for (int i = 0; i < letrasFaltan; i++) { // Engadir as letras ao xogador e retiralas
                                                     // da lista de letras dispoñibles
                num = rnd.nextInt(tablaLetras.size());

                x.getLetras().add(tablaLetras.get(num).toLowerCase());
                tablaLetras.remove(num);

            }

        }

    }

    /**
     * Método que se imprime antes de pedir unha palabra para amosar información do
     * turno e o xogador
     * 
     * @param xogador o xogador do turno.
     */
    private void imprimirInfoTurno() {

        System.out.println("Puntuacións: ");

        for(int i = 0; i < xogadores.length; i++ ) {

            System.out.println(" - " + xogadores[i]);

        }

        System.out.println("_______________________");
        System.out.println("#     TABOLEIRO       #");
        System.out.println("_______________________");

        imprimirTaboleiro();

        System.out.println("_______________________");
        System.out.println("## Turno de " + EntradaSaida.stringColoreada(xogadores[numTurno].getNome(), EntradaSaida.AZUL) + " ##");
        System.out.println("_______________________");
        System.out.println("  - Letras      : " + xogadores[numTurno].getLetras());
        System.out.println("_______________________");

    }

    /**
     * Método para imprimir o taboleiro durante a partida
     */
    private void imprimirTaboleiro() {

        String str;

        System.out.print("   ");

        for (int i = 1; i <= NUM_FILAS; i++) { // numeración das columnas

            str = EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA) + " ";

            if (i < 10) {

                str = " " + str;

            }

            System.out.print(str);

        }

        System.out.print("\n   ");

        for (int i = 1; i <= NUM_FILAS; i++) { // Liña debaixo da numeración das columnas

            System.out.print("___");

        }

        System.out.println("");

        for (int i = 0; i < NUM_FILAS; i++) { // Imprimir os valores do taboleiro fila por fila

            Casilla[] lista = taboleiro[i];

            str = EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA) + "|";

            if ((i + 1) < 10) { // Imprimir o número de fila

                str = " " + str;

            }

            System.out.print(str);

            for (Casilla c : lista) { // Imprimir as casillas da fila

                System.out.print(c.valorMostra() + " ");

            }

            if ((i + 1) < 10) { // Imprimir o número de fila tamén ao final

                str = "| " + EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA);

            } else {

                str = "|" + EntradaSaida.stringColoreada(Integer.toString(i + 1), EntradaSaida.VIOLETA);

            }

            System.out.print(str + "\n");

        }

        System.out.print("   ");

        for (int i = 1; i <= NUM_FILAS; i++) { // Liña enriba da numeración das columnas

            System.out.print("___");

        }

        System.out.print("\n   ");

        for (int i = 1; i <= NUM_FILAS; i++) { // numeración das columnas

            str = EntradaSaida.stringColoreada(Integer.toString(i), EntradaSaida.VIOLETA) + " ";

            if (i < 10) {

                str = " " + str;

            }

            System.out.print(str);

        }

        System.out.println("");
    }

}
