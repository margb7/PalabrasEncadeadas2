package scrabbleii;

/**
 * Clase main do Scrabble 
 * @author mariogb
 */
public class ScrabbleII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Partida p;
        Xogador x1, x2;
        System.out.println("== SCRABBLE ==");

        System.out.println("Xogador 1 : ");
        x1 = pedirXogador();
        System.out.println("Xogador 2 : ");
        x2 = pedirXogador();

        p = new Partida(x1, x2);
        p.xogarPartida();

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
