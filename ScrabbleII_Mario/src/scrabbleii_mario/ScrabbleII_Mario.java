/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scrabbleii_mario;

/**
 *
 * @author mariogb
 */
public class ScrabbleII_Mario {

    /**
     * @param args the command line arguments
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

                //p.setMaxRendicions((byte) numRendicions);
                //p.setPuntosVictoria(puntosVictoria);

                xogo = new Xogo(xogadores, numX2, numX3, numX4, numX2Pal, puntosVictoria, (byte) numRendicions, size);

            } else {

                xogo = new Xogo(xogadores);

            }

            xogo.xogarPartida();
           
            System.out.println("Queres xogar outra partida ? (S / N)");

            sair = !EntradaSaida.pedirConfirmacion();

        } while(!sair);

    }

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