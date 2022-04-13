package scrabbleii_mario;

import java.util.ArrayList;
import java.util.Random;


/**
 * Clase que xestiona unha partida.
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
    
    /**
     * Constructor para crear unha partida a partir dos xogadores. Este constructor establece os valores por defecto
     * do modo normal.
     * @param xogadores os xogadores que interveñen na partida.
     */
    public Xogo(Xogador[] xogadores) {
        this.xogadores = xogadores;

        NUM_FILAS = 21;
        MODO_NORMAL = true;
        maxRendicions = 3;
        iniciarTaboleiro(5, 4, 3, 2);
        engadirLetras();

    }

    /**
     * Constructor para configurar a partida.
     * @param xogadores os xogadores da partida.
     * @param numX2 o numero de casillas X2 no taboleiro.
     * @param numX3 o numero de casillas X3 no taboleiro.
     * @param numX4 o numero de casillas X4 no taboleiro.
     * @param numX2Pal o numero de casillas de doble valor da palabra no taboleiro.
     * @param puntosVictoria o número de puntos para gañar.
     * @param maxRendicions o número máximo de veces que se pode pasar.
     * @param numFilas o número de filas do taboleiro.
     */
    public Xogo(Xogador[] xogadores, int numX2, int numX3, int numX4, int numX2Pal, int puntosVictoria, byte maxRendicions ,int numFilas ) {
        this.xogadores = xogadores;

        this.puntosVictoria = puntosVictoria;
        this.maxRendicions = maxRendicions;

        NUM_FILAS = numFilas;
        MODO_NORMAL = false;
        iniciarTaboleiro(numX2, numX3, numX4, numX2Pal);
        engadirLetras();
    }

    /**
     * Método para preparar o taboleiro para xogar a partida.
     * @param numX2 o numero de casillas X2 no taboleiro.
     * @param numX3 o numero de casillas X3 no taboleiro.
     * @param numX4 o numero de casillas X4 no taboleiro.
     * @param numX2Pal o numero de casillas de doble valor de palabra no taboleiro.
     */
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

    /**
     * Método para colocar casillas especiais en posicións aleatorias do taboleiro.
     * @param tipo o tipo de casilla especial.
     * @param cantidade o número de casillas a colocar.
     */
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

    /**
     * Método que se encarga de xestionar unha partida enteira.
     */
    public void xogarPartida() {

        boolean rematada = false;
        Xogador xogadorTurno;

        for(Xogador x : xogadores ) {

            repartirLetras(x);

        }
        
        numTurno = 0;
        primerTurno = true;

        do {

            xogadorTurno = seguinteXogador();
            repartirLetras(xogadorTurno);
            xogarTurno(xogadorTurno);

            if(xogadorTurno.getNumPasos() > maxRendicions ) {

                xogadorTurno.setEstado(false);

            }

            if(tablaLetras.size() == 0 && xogadorTurno.getLetras().size() == 0 ) {

                xogadorTurno.setEstado(false);

            }

            rematada = comprobarFinal();

        } while(!rematada);

        mostrarResultados();

    } 
    
    /**
     * Método que devolve o seguinte xogador do turno.
     * @return o xogador.
     */
    private Xogador seguinteXogador() {
        Xogador out;

        do {

            out = xogadores[numTurno];
            numTurno++;

            if(numTurno == xogadores.length ) {

                numTurno = 0;
    
            }

        }while(!out.getEstado());
        
        return out;
    }
    
    /**
     * Método que xestiona o que pasa durante un turno.
     * @param xogadorTurno o xogador do turno.
     */
    private void xogarTurno(Xogador xogadorTurno) {

        boolean correcto;
        boolean paso = false;
        boolean horizontal;
        byte fila, columna;     // Onde colocar a palabra
        String palabra; 

        do {

            correcto = true;
            imprimirInfoTurno(xogadorTurno);
            
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
                    int puntos = colocarPalabra(Utilidades.convertirEnCasillas(palabra), fila, columna, horizontal, xogadorTurno);

                    System.out.println("");
                    System.out.println(EntradaSaida.stringColoreada(xogadorTurno.getNome(), EntradaSaida.AZUL) + " obtivo " + puntos + " pola palabra " + EntradaSaida.stringColoreada(palabra, EntradaSaida.VERDE));
                    System.out.println("");
                    
                    xogadorTurno.aumentarPuntuacion(puntos);

                    if(primerTurno ) {

                        primerTurno = false;

                    }

                } else {

                    correcto = false;

                }

            } else {    // Xogador pasou de turno 

                xogadorTurno.aumentarPasos();

            }

        } while(!correcto && !paso);

    }

    /**
     * Método que comproba se unha palabra pode colocarse no taboleiro. Para que unha palabra se poda colocar
     * ten que entrar dentro dos límites do taboleiro e ten que coincidir cunha das letras xa colocadas polo 
     * menos unha vez e como máximo a lonxitude da palabra -1.
     * @param palabra a palabra para comprobar.
     * @param fila a fila onde se quere colocar.
     * @param columna a columna onde se quere colocar.
     * @param horizontal se se coloca horizontal ou verticalmente.
     * @param xog o xogador que vai colocar a palabra.
     * @return true se se pode colocar.
     */
    private boolean podeColocarPalabra(String palabra, byte fila, byte columna, boolean horizontal, Xogador xog ) {
        boolean out = true;
        boolean usouComodin = false;
        byte numCoincidencias = 0;
        Casilla[] convertida = Utilidades.convertirEnCasillas(palabra);
        ArrayList<String> letrasXogador = Utilidades.copiarArray(xog.getLetras());
        
        out = comprobarForaBordes(convertida.length, horizontal, fila, columna);

        if(out) {

            Casilla pezaTaboleiro;

            for(int i = 0; i < convertida.length && out; i++ ) {

                if(horizontal ) {

                    pezaTaboleiro = taboleiro[fila][columna + i];

                } else {

                    pezaTaboleiro = taboleiro[fila + i][columna];

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

    /**
     * Método para colocar unha palabra no taboleiro. Devolve o número de puntos obtidos ao colocar a palabra. Tamén lle retira ao xogador as letras que usa.
     * @param palabra a palabra a colocar
     * @param fila a fila na que colocar a palabra
     * @param columna a columna na que colocar a palabra
     * @param horizontal se é true colocase a palabra horizontalmente.
     * @param xog o xogador que quere colocar a palabra.
     * @return o número de puntos obtidos.
     */
    private int colocarPalabra(Casilla[] palabra, byte fila, byte columna, boolean horizontal, Xogador xog ) {
        int puntos = 0;
        int coincidenciasParaScrabble = 0;
        boolean palabraDobre = false;
        boolean usouComodin;
        Casilla casTaboleiro;

        for(int i = 0; i < palabra.length; i++ ) {

            usouComodin = false;       // Para non puntuar comodins

            if(horizontal ) {

                casTaboleiro = taboleiro[fila][columna + i];

            } else {

                casTaboleiro = taboleiro[fila + i][columna];

            }

            if(casTaboleiro.getTipo() == 4 ) {

                palabraDobre = true;

            }

            if(!palabra[i].getContido().equals(casTaboleiro.getContido()) ) {

                int index = xog.getLetras().indexOf(palabra[i].getContido());

                if(index != -1) {

                    xog.getLetras().remove(index);
                    coincidenciasParaScrabble++;

                } else {

                    usouComodin = true;
                    xog.getLetras().remove("*");

                }

            }
            
            if(!usouComodin ) {

                puntos += Scrabble.puntuacionCasilla(palabra[i]) * casTaboleiro.getMultiplicador();



            }

            if(horizontal ) {

                taboleiro[fila][columna + i] = palabra[i];

            } else {

                taboleiro[fila + i][columna] = palabra[i];

            }

        }

        if(palabraDobre ) {

            puntos *= 2;
            System.out.println("- Valor dobre da palabra");

        }

        if(coincidenciasParaScrabble == 7 && coincidenciasParaScrabble == palabra.length) {

            System.out.println("- SCRABBLE ( +50 puntos)");
            puntos += 50;

        }

        return puntos;
    }

    /**
     * Método para comprobar se rematou a partida.
     * @return true se rematou.
     */
    private boolean comprobarFinal() {
        boolean out = false;
        int numXogadoresPodenXogar = 0;
        int numXogadoresSenLetras = 0;

        for(Xogador xog : xogadores ) {

            if(xog.getEstado() ) {

                numXogadoresPodenXogar++;

            } else {

                if(xog.getNumPasos() < maxRendicions ) {    

                    numXogadoresSenLetras++;

                }

            }

        }

        if(numXogadoresPodenXogar == 0 ) {

            out = true;

        } else if (numXogadoresPodenXogar == 1) {

            if(numXogadoresSenLetras == 0 ) {

                out = true;

            }

        }

        if(!out && !MODO_NORMAL ) {

            for(Xogador x : xogadores ) {

                if(x.getPuntos() >= puntosVictoria ) {

                    out = true;

                }

            }

        }

        return out;
    }

    /**
     * Método que comproba se ao colocar unha palarba se sae dos límites do taboleiro.
     * @param lonxitude a lonxitude da palabra para comprobar. Importante que sexa a lonxitude da palabra
     * xa convertida en casillas e non a lonxitude da palabra como cadea.
     * @param horizontal se se coloca horizontalmente.
     * @param fila a fila onde se coloca.
     * @param columna a columna onde se coloca.
     * @return true se non se sae dos límites.
     */
    private boolean comprobarForaBordes(int lonxitude, boolean horizontal, byte fila, byte columna) {

        boolean out = true;

        if (horizontal) {

            if ((columna + lonxitude) > NUM_FILAS) {

                out = false;

            }

        } else {

            if ((fila + lonxitude) > NUM_FILAS) {

                out = false;

            }

        }

        return out;
    }

    /**
     * Método para pedir se o xogador quere colocar a palabra en vertical ou horizontal.
     * @return true se a quere colocar horizontalmente.
     */
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
    
    /**
     * Método para amosar o resultado da partida ao rematar.
     */
    private void mostrarResultados() {

        int numXogadoresPodenXogar = 0;
        int numXogadoresSenLetras = 0;
        int offset = 1;     // Num de xogadores empatados    

        System.out.println("\n==PARTIDA REMATADA==\nRESULTADOS\n");

        Utilidades.ordenarPorPuntos(xogadores);

        for(Xogador xog : xogadores ) {

            if(xog.getEstado() ) {

                numXogadoresPodenXogar++;

            } else {

                if(xog.getNumPasos() < maxRendicions ) {    

                    numXogadoresSenLetras++;

                }

            }

        }

        if(numXogadoresSenLetras == 0 && numXogadoresPodenXogar == 1 ) {    // Rendíronse todos menos 1 

            System.out.println("Gañou " + xogadores[xogadores.length - 1]);

        } else if(numXogadoresSenLetras == xogadores.length ) {
            
            if(xogadores[xogadores.length - 1].getPuntos() != xogadores[xogadores.length - 2].getPuntos() ) {

                System.out.println("Gañou " + xogadores[xogadores.length]);

            } else {

                int puntuacion = xogadores[xogadores.length - 1].getPuntos();
                boolean salir = false;

                for(int i = xogadores.length - 2 ; i >= 0 && !salir; i-- ) {

                    if(xogadores[i].getPuntos() == puntuacion ) {

                        offset++;

                    } else {

                        salir = true;

                    }

                }

            }
            
        } else if(!MODO_NORMAL ) {

            System.out.println("Gañou " + xogadores[xogadores.length - 1]);

        }
        
        System.out.println("Resultados do resto: ");

        for(int i = xogadores.length - 1 - offset; i >= 0 ; i-- ) {

            System.out.println(" - " + xogadores[i]);

        }

    }

    /**
     * Método para repartir letras a un xogador. Se non quedan letras non repartirá.
     * @param xog o xogador para darlle as letras ata 7 ou as que queden dispoñibles.
     */
    private void repartirLetras(Xogador xog ) {

        if (xog.getLetras().size() < 7) {

            Random rnd = new Random();
            int letrasFaltan = 7 - xog.getLetras().size();
            int num;

            if (letrasFaltan > tablaLetras.size()) { // Se non quedan suficientes letras para repartir

                letrasFaltan = tablaLetras.size(); // Repartir as que quedan -> se non queda non entra no bucle for

            }

            for (int i = 0; i < letrasFaltan; i++) { // Engadir as letras ao xogador e retiralas
                                                     // da lista de letras dispoñibles
                num = rnd.nextInt(tablaLetras.size());

                xog.getLetras().add(tablaLetras.get(num).toLowerCase());
                tablaLetras.remove(num);

            }

        }

    }

    /**
     * Método que se imprime antes de pedir unha palabra para amosar información do
     * turno e do xogador.
     * @param xogador o xogador do turno.
     */
    private void imprimirInfoTurno(Xogador xogTurno) {

        System.out.println("Puntuacións: ");

        for(int i = 0; i < xogadores.length; i++ ) {

            System.out.println(" - " + xogadores[i]);

        }

        System.out.println("_______________________");
        System.out.println("#     TABOLEIRO       #");
        System.out.println("_______________________");

        imprimirTaboleiro();

        System.out.println("_______________________");
        System.out.println("## Turno de " + EntradaSaida.stringColoreada(xogTurno.getNome(), EntradaSaida.AZUL) + " ##");
        System.out.println("  - Letras      : " + xogTurno.getLetras());
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