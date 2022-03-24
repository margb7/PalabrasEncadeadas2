package scrabbleii;

public class Xogador {
    
    private String nome;
    private int puntos;

    public Xogador(String nome) {

        this.nome = nome;
        puntos = 0;

    }

    public String getNome() {
        
        return nome;
    } 

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = (puntos >= 0)? puntos : 0;
    }

    public void sumarPuntos(int puntos ) {
        this.puntos = (puntos >= 0)? (this.puntos + puntos) : puntos;
    }

    @Override
    public String toString() {
        return nome + " con " + puntos + " puntos"; 
    }

}
