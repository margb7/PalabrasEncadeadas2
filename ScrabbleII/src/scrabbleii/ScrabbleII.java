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

        boolean sair = false;
        Partida p;
        Xogador x1, x2;
        char op;
        
        System.out.println("== SCRABBLE ==");

        do {

            System.out.println("Xogador 1 : ");
            x1 = pedirXogador();
            
            System.out.println("Xogador 2 : ");
            x2 = pedirXogador();

            p = new Partida(x1, x2);
            p.xogarPartida();

            System.out.println("Queres xogar outra partida ? (S / N)");

            do {

                op = Character.toLowerCase(EntradaSaida.lerChar());

                if(op != 's' && op != 'n' ) {

                    EntradaSaida.imprimirErro("Ten que ser s ou n");

                }

            } while(op != 's' && op != 'n');

            if(op == 'n' ) {

                sair = true;

            } 

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
