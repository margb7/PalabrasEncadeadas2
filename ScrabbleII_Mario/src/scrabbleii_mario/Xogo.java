package scrabbleii_mario;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nocelo
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

        do {

            imprimirInfoTurno();

            xogadorTurno = seguinteXogador();

            xogarTurno(xogadorTurno);

        } while(!rematada);

        mostrarResultados();

    } 
    
    /**
     * Método que devolve o seguinte xogador do turno.
     * 
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
        String palabra;

        do {

            correcto = true;
            
            

        } while(!correcto && !paso);

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

        System.out.print("Puntuacións: ");

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
        System.out.println("Introduce unha palabra cun mínimo de " + Scrabble.LON_MIN + " letras ou \"0\" para rendirte");

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
