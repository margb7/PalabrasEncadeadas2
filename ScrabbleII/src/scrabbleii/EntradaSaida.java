package scrabbleii;

import java.util.Scanner;

import javax.print.attribute.standard.Copies;


/**
 * Clase que xestiona a entrada e saída pola terminal.
 * @author a21mariogb
 */
public class EntradaSaida {
    
    private static final Scanner input = new Scanner(System.in,"ISO-8859-1");
    
    private static final String RESET = "\u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String VERMELLO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String VIOLETA = "\u001B[35m";
    public static final String CIAN = "\u001B[36m";
    public static final String BRANCO = "\u001B[37m";
    
    private static void error( String tipoEsperado ) {
        imprimirErro("Esperábase un " + tipoEsperado);
    } 
    
    public static long lerLong() {

        long salida;
            
        do {

            if (input.hasNext() && !input.hasNextLong() ) {
                error("long");
                input.nextLine();
            }

        } while (!input.hasNextLong());

        salida = input.nextLong();
        input.nextLine();

        return salida;

    }

    public static int lerInt() {
        
        int salida;

        do {

            if (input.hasNext() && !input.hasNextInt() ) {
                error("int");
                input.nextLine();
            }

        } while (!input.hasNextInt());

        salida = input.nextInt();
        input.nextLine();

        return salida;

    }

    public static short lerShort() {

        short salida;


        do {

            if (input.hasNext() && !input.hasNextShort() ) {
                error("short");
                  input.nextLine();
            }

        } while (!input.hasNextShort());

        salida = input.nextShort();
        input.nextLine();

        return salida;

    }

    public static byte lerByte() {

        byte salida;


        do {

            if (input.hasNext() && !input.hasNextByte() ) {
                error("byte");
                input.nextLine();
            }

        } while (!input.hasNextByte());

        salida = input.nextByte();
        input.nextLine();

        return salida;        

    }

    public static float lerFloat() {
        
        float salida;

        do {

            if (input.hasNext() && !input.hasNextFloat() ) {
                error("float");
                input.nextLine();
            }

        } while (!input.hasNextFloat());

        salida = input.nextFloat();
        input.nextLine();

        return salida; 
    }

    public static double lerDouble() {

        double salida;

        do {

            if (input.hasNext() && !input.hasNextDouble() ) {
                error("double");
                input.nextLine();
            }

        } while (!input.hasNextDouble());

        salida = input.nextDouble();
        input.nextLine();

        return salida; 

    }

    public static boolean lerBoolean() {
        
	    
        boolean salida;
        
        do {

            if (input.hasNext() && !input.hasNextBoolean() ) {
                error("boolean");
                input.nextLine();
            }

        } while (!input.hasNextBoolean());

        salida = input.nextBoolean();
        input.nextLine();

        return salida; 

    }
    
    public static String lerString() {
        return input.nextLine();
    }
    
    public static char lerChar() {
        String str = input.nextLine();
        char c;
        
        if(str.length() == 0 ) {
            
            c = ' ';
            
        } else {
            
            c = str.charAt(0);
            
        }
        
	return c;
    }
    
    public static int pedirRango(int min, int max) {
        int out;
        boolean correcto;
        
        do {
            correcto = true;
            out = lerInt();
            
            if(out < min || out > max ) {
                
                imprimirErro("Ten que estar no rango [" + min + "," + max + "]");
                correcto = false;
                
            }
            
        } while(!correcto);
        
        return out;
    }

    public static boolean pedirConfirmacion() {
        boolean correcto;
        char op;

        do {
            correcto = true;
            op = Character.toLowerCase(lerChar());

            if(op != 's' && op != 'n' ) {

                correcto = false;

                imprimirErro("Ten que ser 's' ou 'n'");

            }

        } while(!correcto);
        
        return correcto;
    }
    
    public static void imprimirErro(String mensaxe ) {
        System.out.println(VERMELLO + mensaxe + RESET);
    }
    
    public static void imprimirCor(String mensaxe, String cor ) {
        System.out.println(stringColoreada(mensaxe, cor));
    }
    
    public static String stringColoreada(String mensaxe, String cor) {
        
        String str;
        
        if(cor.contains("\u001B") ) {
            
            str = cor + mensaxe + RESET;
            
        } else {
            
            str = mensaxe;
            
        }
        
        return str;
    }
}
