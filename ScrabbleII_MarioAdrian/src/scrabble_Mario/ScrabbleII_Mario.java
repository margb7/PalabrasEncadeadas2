package scrabble_Mario;

/**
 * Clase main do Scrabble.
 * As melloras incluídas son:
 * <ul>
 *      <li>Pedir as probabilidades de que aparezcan as distintas casillas especiais</li>
 *      <li>Pedir o tamaño do taboleiro.</li>
 *      <li>Os comodíns colócanse automaticamente. So se pide unha confirmación no caso de usar 
 *      un ou mais.</li>
 *      <li>Inclúese os puntos de victoria. O primer xogador en chegar a eses puntos será o gañador.</li>
 * </ul>
 * @author mariogb
*/
public class ScrabbleII_Mario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean sair = false;
        Partida p;
        Xogador x1, x2;
        
        EntradaSaida.imprimirCor("  ==============", EntradaSaida.VERDE);
        EntradaSaida.imprimirCor("==== SCRABBLE ====", EntradaSaida.VERDE);
        EntradaSaida.imprimirCor("  ==============", EntradaSaida.VERDE);
        System.out.println("");

        do {

            System.out.println("Xogador 1 : ");
            x1 = pedirXogador();
            
            System.out.println("Xogador 2 : ");
            x2 = pedirXogador();

            System.out.println("\nQueres configurar axustes da partida? (S / N)");

            if(EntradaSaida.pedirConfirmacion() ) {

                int size;
                int numRendicions;
                int puntosVictoria;
                float probx2;
                float probx3;
                float probx4;

                System.out.println("- Introduce o tamaño do taboleiro [10, 30]:");
                size = EntradaSaida.pedirRango(10, 30);

                System.out.println("- Introduce a probabilidade dun x2 [0, 0.2]:");
                probx2 = EntradaSaida.pedirRango(0F, 0.2F);

                System.out.println("- Introduce a probabilidade dun x3 [0, 0.2]:");
                probx3 = EntradaSaida.pedirRango(0F, 0.2F);

                System.out.println("- Introduce a probabilidade dun x4 [0, 0.2]:");
                probx4 = EntradaSaida.pedirRango(0F, 0.2F);

                p = new Partida(x1, x2, probx2, probx3, probx4, size);

                System.out.println("- Introduce o número máximo de veces que se pode pasar [1, 20]:");
                numRendicions = EntradaSaida.pedirRango(1, 20);

                System.out.println("- Introduce o número de puntos para gañar [10, 1000]:");
                puntosVictoria = EntradaSaida.pedirRango(10, 1000);

                p.setMaxRendicions((byte) numRendicions);
                p.setPuntosVictoria(puntosVictoria);

            } else {

                p = new Partida(x1, x2);

            }

            p.xogarPartida();
           
            System.out.println("Queres xogar outra partida ? (S / N)");

            sair = !EntradaSaida.pedirConfirmacion();

        } while(!sair);

    }

    private static Xogador pedirXogador() {
        String str;

        System.out.print("- Introduce o nome : ");

        do {

            str = EntradaSaida.lerString();

            if(str.length() < 3 ) {

                EntradaSaida.imprimirErro("O nome debe ter como mínimo 3 caracteres");

            }

        } while(str.length() < 3);

        return new Xogador(str);
    }
    
}
