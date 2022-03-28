/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package scrabbleii;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mariogb
 */
public class XogadorTest {
    
    private static Xogador xog;

    public XogadorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
        xog = new Xogador("XogadorTest");

    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of getNome method, of class Xogador.
     */
    @Test
    public void testC1() {

        String[] contido = {"h", "o", "l", "a"};
        String palabra = "hola";
        byte numComodins = 0;

        testXenerico(contido, palabra,true, numComodins);

    }

    @Test
    public void testC2() {

        String[] contido = {"h", "", "l", "a"};
        String palabra = "hola";
        byte numComodins = 0;

        testXenerico(contido, palabra, false , numComodins);

    }

    @Test
    public void testC3() {

        String[] contido = {"h", "o", "l", "a"};
        String palabra = "es pa ciado";
        byte numComodins = 0;

        testXenerico(contido, palabra, false, numComodins);

    }

    @Test
    public void testC4() {

        String[] contido = {"h", "e", "l", "l", "o"};
        String palabra = "hola";
        byte numComodins = 0;

        testXenerico(contido, palabra, false, numComodins);

    }

    @Test
    public void testC5() {

        String[] contido = {"h", "o"};
        String palabra = "hola";
        byte numComodins = 2;

        testXenerico(contido, palabra,true, numComodins);

    }

    private void testXenerico(String[] contido, String palabra,boolean esperado, byte numComodins) {

        ArrayList<String> arr = new ArrayList<>();
        arr.addAll(contido);

        xog.setLetras(arr);

        assertEquals(esperado, xog.podeColocarPalabra(palabra, numComodins));

    }
    
}
