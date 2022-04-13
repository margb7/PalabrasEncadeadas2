package scrabbleii_mario;

import java.util.ArrayList;


/**
 * Clase coa información do xogador
 */
public class Xogador {
    private String nome;
    private int numPuntos;
    private ArrayList<String> letras;
    private byte numPasos;
    private boolean activo;
    
    /**
     * Constructor para crear un xogador a partir do seu nome. 
     * @param nome o nome do xogador.
     */
    public Xogador(String nome) {
        this.nome = nome;
        activo = true;
        numPasos = 0;
        letras = new ArrayList<>();
    }

    /**
     * Getter das letras que posúe o xogador para realizar unha xogada.
     * @return un arrayList das letras.
     */
    public ArrayList<String> getLetras() {
        return letras;
    }
    
    /**
     * Getter para obter o nome do xogador.
     * @return o nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Getter para obter a puntuación do xogador.
     * @return a puntuación.
     */
    public int getPuntos() {
        return numPuntos;
    }

    /**
     * Getter para obter o número de pasos de turnos que realizou o xogador.
     * @return o numero de pasos.
     */
    public byte getNumPasos() {
        return numPasos;
    }

    /**
     * Getter para saber o se o xogador pode seguir xogando na partida.
     * @return true se pode seguir xogando.
     */
    public boolean getEstado() {
        return activo;
    }

    /**
     * Setter para establecer o estado do xogador. 
     * @param activo o novo estado do xogador
     */
    public void setEstado(boolean activo) {
        this.activo = activo;
    }

    /**
     * Setter para cambiar o arrayList de letras do xogador.
     * @param letras o novo arrayList para o xogador.
     */
    public void setLetras(ArrayList<String> letras) {
        this.letras = letras;
    }

    /**
     * Método para incrementar o número de veces que o xogador pasou de turno en 1.
     */
    public void aumentarPasos() {
        numPasos++;
    } 

    /**
     * Método para sumar puntos ao xogador. Se os puntos agregados son menores que 0 os puntos do xogador
     * pasarán a ser 0.
     * @param puntos os puntos a sumar
     */
    public void aumentarPuntuacion(int puntos) {
        numPuntos += puntos;
    }

    /**
     * Método toString para mostrar o estado do xogador.
     */
    public String toString() {
        return EntradaSaida.stringColoreada(nome, EntradaSaida.AZUL) + " con " + numPuntos + " puntos";
    }

}
