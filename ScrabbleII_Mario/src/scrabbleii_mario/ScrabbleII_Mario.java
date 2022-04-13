package scrabbleii_mario;

/**
 * Clase main do Scrabble.
 * As melloras incluídas son:
 * <ul>
 *      <li>Pódese configurar o número de cada casilla especial que aparece no taboleiro</li>
 *      <li>Pódese configurar o tamaño do taboleiro</li>
 *      <li>Os comodíns colócanse automáticamente no caso de precisar ser usados</li>
 *      <li>As partidas poden conter ata 4 xogadores</li>
 *      <li>Pódese configurar un número de puntos para gañar. O primer xogador en chegar a eses puntos será o gañador.</li>
 * </ul>
 * Exceptuando a mellora para xogar ata 4 xogadores, o resto de melloras son so accesibles ao comezo da 
 * partida cando se pregunta se se quere configurar a partida. 
*/
public class ScrabbleII_Mario {

    /**
     * Main do programa
     * @param args os argumentos por liña de comandos.
     */
    public static void main(String[] args) {
        
        boolean sair = false;
        int numXog;
        Xogo xogo;
        Xogador[] xogadores;
        
        EntradaSaida.imprimirCor("  ==============", EntradaSaida.VERDE);
        EntradaSaida.imprimirCor("==== SCRABBLE ====", EntradaSaida.VERDE);
        EntradaSaida.imprimirCor("  ==============", EntradaSaida.VERDE);
        System.out.println("");

        do {

            System.out.println("Indica o número de xogadores [2,4]: ");
            numXog = EntradaSaida.pedirRango(2, 4);
            xogadores = new Xogador[numXog];

            for(int i = 0; i < numXog; i++ ) {

                xogadores[i] = pedirXogador(i);

            }
            
            System.out.println("\nQueres configurar axustes da partida? (S / N)");

            if(EntradaSaida.pedirConfirmacion() ) {

                int size;
                int numRendicions;
                int puntosVictoria;
                int numX2, numX3, numX4, numX2Pal;

                System.out.println("- Introduce o tamaño do taboleiro [10, 30]:");
                size = EntradaSaida.pedirRango(10, 30);

                System.out.println("- Introduce o número de casillas x2 [0, 10]:");
                numX2 = EntradaSaida.pedirRango(0, 10);

                System.out.println("- Introduce o número de casillas dun x3 [0, 10]:");
                numX3 = EntradaSaida.pedirRango(0, 10);

                System.out.println("- Introduce o número de casillas x4 [0, 10]:");
                numX4 = EntradaSaida.pedirRango(0, 10);

                System.out.println("- Introduce o número de casillas 2P (valor doble da palabra) [0, 4]:");
                numX2Pal = EntradaSaida.pedirRango(0, 4);

                System.out.println("- Introduce o número máximo de veces que se pode pasar [1, 20]:");
                numRendicions = EntradaSaida.pedirRango(1, 20);

                System.out.println("- Introduce o número de puntos para gañar [10, 500]:");
                puntosVictoria = EntradaSaida.pedirRango(10, 500);

                xogo = new Xogo(xogadores, numX2, numX3, numX4, numX2Pal, puntosVictoria, (byte) numRendicions, size);

            } else {

                xogo = new Xogo(xogadores);

            }

            xogo.xogarPartida();
           
            System.out.println("Queres xogar outra partida ? (S / N)");

            sair = !EntradaSaida.pedirConfirmacion();

        } while(!sair);

    }

    /**
     * Método para pedir un xogador.
     * @param num o número de xogador que representa. Este valor non afecta ao xogador e só é un valor de mostra.
     * @return un obxecto xogador.
     */
    private static Xogador pedirXogador(int num) {
        String str;

        System.out.print("- Introduce o nome do xogador " + (num + 1) + ": ");

        do {

            str = EntradaSaida.lerString();

            if(str.length() < 3 ) {

                EntradaSaida.imprimirErro("O nome debe ter como mínimo 3 caracteres");

            }

        } while(str.length() < 3);

        return new Xogador(str);
    }

}