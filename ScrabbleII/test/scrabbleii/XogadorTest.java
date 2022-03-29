/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package scrabbleii;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Tests unitarios para o método "podeColocarPalabra()" da clase xogador
 * @author mariogb
 */
public class XogadorTest {
    
    private static Xogador xog;

    public XogadorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        xog = new Xogador("XogadorTest");

    }

    /**
     * Test of getNome method, of class Xogador.
     */
    @Test
    public void testC1() {

        String[] contido = {"h", "o", "l", "a"};
        String palabra = "hola";
        
        testMetodo(contido, palabra,(byte)0 );

    }

    @Test
    public void testC2() {

        String[] contido = {"h", "", "l", "a"};
        String palabra = "hola";

        testMetodo(contido, palabra, (byte)1);

    }

    @Test
    public void testC3() {

        String[] contido = {"h", "o", "l", "a"};      
        String palabra = "espaciado";
        
        testMetodo(contido, palabra, (byte)7);

    }

    @Test
    public void testC4() {

        String[] contido = {"h", "e", "l", "l", "o"};
        String palabra = "hola";

        testMetodo(contido, palabra, (byte)1);

    }

    @Test
    public void testC5() {

        String[] contido = {"h", "o"};
        String palabra = "hola";

        testMetodo(contido, palabra,(byte)2);

    }

    private void testMetodo(String[] contido, String palabra,byte esperado) {

        ArrayList<String> arr = new ArrayList<>();
        
        for(int i = 0; i < contido.length; i++ ) {

            arr.add(contido[i]);

        }
        
        xog.setLetras(arr);
        
        assertEquals(esperado, xog.podeColocarPalabra(palabra));

    }
    
}
