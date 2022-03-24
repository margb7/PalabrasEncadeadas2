/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrabbleii;

/**
 *
 * @author a21mariogb
 */
public class Menu {
    
    private String[] opcionesMenu;
    private String titular;
    private String separadorNumero;
    private int numEspacios;
    
    
    /**
     * Constructor coas opcións do menú
     * @param opcionesMenu array de Strings coas opcións do menú
     */
    public Menu(String[] opcionesMenu ) {
        this("MENÚ", 0,". ", opcionesMenu);
    }
     /**
     * Constructor ao que se especifica o titular
     * @param titular texto que aparecerá ao inicio do menú
     * @param opcionesMenu array de Strings coas opcións do menú
     */
    public Menu(String titular, String[] opcionesMenu ) {
        this(titular,0 ,". ", opcionesMenu);
    }
    
    /**
     * Constructor 
     * @param titular titular cada vez que se mostran as opcións 
     * @param separador separador entre os números e as opcións que 
     * representan
     * @param opcionesMenu array de Strings coas opcións do menú
     */
    public Menu(String titular, String separador, String[] opcionesMenu) {
        this(titular, 0, separador, opcionesMenu);
    }
    /**
     * Constructor 
     * @param titular titular cada vez que se mostran as opcións 
     * @param numEspacios número de espazos antes dos números das opcións
     * @param separador separador entre os números e as opcións que 
     * representan
     * @param opcionesMenu array de Strings coas opcións do menú
     */
    public Menu(String titular,int numEspacios, String separador, String[] opcionesMenu) {
        this.titular = titular;
        this.separadorNumero = separador;
        this.opcionesMenu = opcionesMenu;
        this.numEspacios = numEspacios;
    }
    
    /**
     * Getter para o titular. Por defecto é "MENÚ"
     * @return o titular do menú
     */
    public String getTitular() {
        return titular;
    }
    /**
     * Setter para introducir un titular. Por defecto é "MENÚ"
     * @param titular o titular para cambiar
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    /**
     * Método para modificar unha opción do menú.
     * @param cadea cadea para modificar
     * @param num correspóndese co numero da opción a modificar e non co índice no array de opcións
     * @return true se se puido modificar a opción
     */
    public boolean modificarEntrada(String cadea, int num ) {
        boolean out = false;
    
        if(comprobarOpcion(num) ) {
            
            opcionesMenu[num - 1] = cadea;
            out = true;
            
        } 
        
        return out;
    }
    
    /**
     * Devolverá unha cadea co menú. Se se especificou un titular tamén o mostrará
     * @return a cadea co menu
     */
    public String mostrarMenu() {
        
        StringBuilder strb = new StringBuilder("");
        
        strb.append(titular).append("\n");
        
        for(int i = 0; i < opcionesMenu.length; i++ ) {
            
            for(int j = 0; j < numEspacios; j++ ) {
                
                strb.append(" ");
                
            } 
            
            strb.append(i + 1).append(separadorNumero);
            strb.append(opcionesMenu[i]);
            strb.append("\n");
            
        }
        
        return strb.toString();
    }
    /**
     * Método para comprobar se un número se atopa no rango de opcións do menú
     * @param opcion o número a comprobar
     * @return true se se atopa no rango
     */
    public boolean comprobarOpcion(int opcion) {
        
        boolean out = false;
    
        opcion--;
        
        if( opcion >= 0 && opcion < opcionesMenu.length) {
            
            out = true;
            
        }
        
        return out;
    }
    /**
     * Método para pedir unha opción do menú
     * @return devolve o número da opción que sempre se atopará no rango das
     * opcións dispoñibles 
     */
    public int pedirOpcion() {
        
        int op;
        
        System.out.println(mostrarMenu());
        
        do {
            
            System.out.print("Introduce unha opción: ");
            op = EntradaSaida.lerInt();
            
            if(op > opcionesMenu.length || op < 1) {
                
                EntradaSaida.imprimirErro("Fora do rango posible [1," + opcionesMenu.length + "]");
                
            }
            
        } while(op > opcionesMenu.length || op < 1); 
        
        return op;
    }
}
