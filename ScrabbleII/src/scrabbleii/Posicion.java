


package scrabbleii;

public class Posicion {
  
  private String contido;
  private boolean eMultiplicador;
  private byte multiplicador;

  public Posicion(String contido ) {
    
    this.contido = contido;

  }
  
  public String getContido() {
    return contido;
  }

  public boolean estaBaleiro() {
    return contido.equals("");
  }

  @Override
  public String toString()  {
    StringBuilder strb = new StringBuilder("");

    if(multiplicador != 0 && estaBaleiro() {

	strb.append("x").append(multiplicador);

    } else if(!estaBaleiro()) {

	strb.append(contido);


    } else {
	
	strb.append("  ");

    }

    return strb.toString();
  }
